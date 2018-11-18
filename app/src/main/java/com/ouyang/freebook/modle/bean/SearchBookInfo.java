package com.ouyang.freebook.modle.bean;

public class SearchBookInfo {
        private String Id;
        private String Name;
        private String Author;
        private String Img;
        private String Desc;
        private String BookStatus;
        private String LastChapterId;
        private String LastChapter;
        private String CName;
        private String UpdateTime;
        public void setId(String Id) {
            this.Id = Id;
        }
        public String getId() {
            return Id;
        }

        public void setName(String Name) {
            this.Name = Name;
        }
        public String getName() {
            return Name;
        }

        public void setAuthor(String Author) {
            this.Author = Author;
        }
        public String getAuthor() {
            return Author;
        }

        public void setImg(String Img) {
            this.Img = Img;
        }
        public String getImg() {
            return Img;
        }

        public void setDesc(String Desc) {
            this.Desc = Desc;
        }
        public String getDesc() {
            return Desc;
        }

        public void setBookStatus(String BookStatus) {
            this.BookStatus = BookStatus;
        }
        public String getBookStatus() {
            return BookStatus;
        }

        public void setLastChapterId(String LastChapterId) {
            this.LastChapterId = LastChapterId;
        }
        public String getLastChapterId() {
            return LastChapterId;
        }

        public void setLastChapter(String LastChapter) {
            this.LastChapter = LastChapter;
        }
        public String getLastChapter() {
            return LastChapter;
        }

        public void setCName(String CName) {
            this.CName = CName;
        }
        public String getCName() {
            return CName;
        }

        public void setUpdateTime(String UpdateTime) {
            this.UpdateTime = UpdateTime;
        }
        public String getUpdateTime() {
            return UpdateTime;
        }

}
