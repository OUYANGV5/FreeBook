package com.ouyang.freebook.util;

import android.app.Activity;
import android.content.res.Resources;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.util.DisplayMetrics;
import android.widget.TextView;

import com.ouyang.freebook.MyApplication;
import com.ouyang.freebook.R;

import java.util.ArrayList;
import java.util.List;

public class TextViewUtil {
    private static TextPaint textPaint;
    private static DisplayMetrics displayMetrics;
    private static float titleHeight;
    private static float bottomHeight;
    private static float padding;
    static {
        textPaint=new TextPaint();
        Resources resources=MyApplication.getContext().getResources();
        textPaint.setTextSize(resources.getDimension(R.dimen.contentTextSize));
        displayMetrics=MyApplication.getContext().getResources().getDisplayMetrics();
        titleHeight=resources.getDimension(R.dimen.sectionTitleHeight);
        padding=resources.getDimension(R.dimen.contentPadding);
        bottomHeight=resources.getDimension(R.dimen.readBottomHeight);
    }
    /*
        会返回剩下显示不下的内容，如果没有剩下的，则返回null
     */
    @Deprecated
    public static String setTextViewContent(TextView textView,String content){
        textView.setText(content);
        textView.setLines(textView.getMeasuredHeight()/textView.getLineHeight());
        //最底部可见的一行的y坐标
        int lastLineY=textView.getMeasuredHeight()-textView.getPaddingTop()-textView.getPaddingBottom()-textView.getLineHeight();
        Layout layout=textView.getLayout();
        int line=layout.getLineForVertical(lastLineY);//行数
        int num=layout.getLineEnd(line);//可见字数
        textView.setText(content.substring(0,num));
        return content.substring(num,content.length());
    }

    /*
        将从服务器获取的正文内容按分段的形式返回一个集合，每一项都是一个段落
     */
    public static List<String> makeStringToSection(String text){
        if(text==null){
            return null;
        }
        String[] section=text.split("\n");
        List<String> list=new ArrayList<>();
        for(String s:section){
            if(!s.matches("\\s*")){
                list.add(s);
            }
        }
        return list;
    }

    /*
        把一个集合的字符串转成一个字符串
     */
    public static String makeStringListToString(List<String> list,int start,int end){
        StringBuffer stringBuffer=new StringBuffer();
        for(int i=start;i<end;i++){
            stringBuffer.append(list.get(i));
            if(i<end-1){
                stringBuffer.append('\n');
            }
        }
        return stringBuffer.toString();
    }

    /*
        将服务器获取到的文章内容格式化
     */
    public static String formatText(String content){
        List<String> stringList=makeStringToSection(content);
        return makeStringListToString(stringList,0,stringList.size());
    }

    public static List<String> getAllLineIndex(String content){
        StaticLayout staticLayout=new StaticLayout(content,textPaint, (int) (displayMetrics.widthPixels-padding*2),Layout.Alignment.ALIGN_NORMAL,1,textPaint.getFontSpacing(),false);
        List<String> indexList=new ArrayList<>(staticLayout.getLineCount());
        for(int i=0;i<staticLayout.getLineCount();i++){
            indexList.add(content.substring(staticLayout.getLineStart(i),staticLayout.getLineEnd(i)));
        }
        return indexList;
    }
    public static List<String> getAllLineIndexByList(List<String> sectionList){
        List<String> indexList=new ArrayList<>();
        for(String s:sectionList){
            indexList.addAll(getAllLineIndex(s));
        }
        return indexList;
    }
    public static int getPageSizeByChapter(List<String> sectionList, Activity activity){
        int height=displayMetrics.heightPixels;
        TextView textView=new TextView(activity);
        textView.setTextSize(textPaint.getTextSize());
        int lineCount=height/textView.getLineHeight();
        return sectionList.size()/lineCount;
    }
}
