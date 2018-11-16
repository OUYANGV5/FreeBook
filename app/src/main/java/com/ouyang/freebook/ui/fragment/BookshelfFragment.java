package com.ouyang.freebook.ui.fragment;


import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ouyang.freebook.R;
import com.ouyang.freebook.databinding.FragmentBookshelfBinding;
import com.ouyang.freebook.modle.bean.BookDetails;
import com.ouyang.freebook.modle.bean.ResponseData;
import com.ouyang.freebook.modle.litepal.BookBean;
import com.ouyang.freebook.modle.request.BookRequest;
import com.ouyang.freebook.ui.activity.MainActivity;
import com.ouyang.freebook.ui.activity.ReadActivity;
import com.ouyang.freebook.ui.adapter.BookShelfAdapter;
import com.ouyang.freebook.util.RequestUtil;

import org.litepal.LitePal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * A simple {@link Fragment} subclass.
 */
public class BookshelfFragment extends BaseFragment {
    FragmentBookshelfBinding binding;
    BookShelfAdapter bookShelfAdapter;
    BookRequest bookRequest;

    private BookShelfAdapter.BookViewHolder currentViewHolder;
    public BookshelfFragment() {
        // Required empty public constructor
    }

    @Override
    public void init() {
        bookRequest=RequestUtil.get(BookRequest.class);
        bookShelfAdapter=new BookShelfAdapter(new ArrayList<BookBean>());
        bookShelfAdapter.setOnItemClickListener(new BookShelfAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int position, BookShelfAdapter.BookViewHolder holder) {
                Log.e("zxasw",position+"");
                bookRequest.getBookDetails(String.valueOf(holder.getItemBookshelfBookBinding().getBookBean().getBookId()))
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Observer<ResponseData<BookDetails>>() {
                            @Override
                            public void onSubscribe(Disposable d) {

                            }

                            @Override
                            public void onNext(ResponseData<BookDetails> bookDetailsResponseData) {
                                Log.e("zxasw",bookDetailsResponseData.getData().getAuthor());
                                ReadActivity.startReadActivity(getContext(),bookDetailsResponseData.getData());
                            }

                            @Override
                            public void onError(Throwable e) {

                            }

                            @Override
                            public void onComplete() {

                            }
                        });
            }
        });
        binding.recyclerView.setLayoutManager(new GridLayoutManager(getContext(),3));
        binding.recyclerView.setAdapter(bookShelfAdapter);
        int dragDirs=ItemTouchHelper.UP|ItemTouchHelper.DOWN|ItemTouchHelper.LEFT|ItemTouchHelper.RIGHT;
        ItemTouchHelper itemTouchHelper=new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(dragDirs,0) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                //得到需要拖拽item的位置
                int fromPosition = viewHolder.getAdapterPosition();
                //得到item拖拽到目标的位置
                int toPosition = target.getAdapterPosition();

                BookBean bookBeanFrom = bookShelfAdapter.getList().get(fromPosition);
                BookBean bookBeanTo = bookShelfAdapter.getList().get(toPosition);
                if(fromPosition==toPosition){
                    return true;
                }else if (fromPosition > toPosition) {
                    //如果是从下往上拖拽的就依次将需要拖拽的item的位置和目标的位置（拖拽item的位置-1）交换
                    for (int i = fromPosition; i > toPosition; i--) {
                        Collections.swap(bookShelfAdapter.getList(), i, i - 1);
                    }

                } else {
                    //如果是从上往下拖拽的就依次将需要拖拽的item的位置和目标的位置（拖拽item的位置+1）交换
                    for (int i = fromPosition; i < toPosition; i++) {
                        Collections.swap(bookShelfAdapter.getList(), i, i + 1);
                    }
                }
                //修改数据库内容

                long fromId=bookBeanFrom.getId();
                long toId=bookBeanTo.getId();
                bookBeanFrom.setId(toId);
                bookBeanTo.setId(fromId);
                bookBeanFrom.update(toId);
                bookBeanTo.update(fromId);

                //最后通过适配器将item移动
                bookShelfAdapter.notifyItemMoved(fromPosition, toPosition);
                return true;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
            }

            @Override
            public void onSelectedChanged(RecyclerView.ViewHolder viewHolder, int actionState) {
                super.onSelectedChanged(viewHolder, actionState);
                BookShelfAdapter.BookViewHolder bookViewHolder= (BookShelfAdapter.BookViewHolder) viewHolder;
                if(actionState==ItemTouchHelper.ACTION_STATE_DRAG){
                    currentViewHolder=bookViewHolder;
                    currentViewHolder.onDrag();
                }else if(actionState==ItemTouchHelper.ACTION_STATE_IDLE){
                    currentViewHolder.onRelease();
                }
            }
        });
        itemTouchHelper.attachToRecyclerView(binding.recyclerView);
        onVisibleAgain();
    }

    @Override
    public void onVisibleAgain() {
        MainActivity mainActivity= (MainActivity) getActivity();
        mainActivity.setToolbar(binding.toolbar,true);
        List<BookBean> all = LitePal.findAll(BookBean.class);
        bookShelfAdapter.updateList(all);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding=DataBindingUtil.inflate(inflater,R.layout.fragment_bookshelf,container,false);
        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

}
