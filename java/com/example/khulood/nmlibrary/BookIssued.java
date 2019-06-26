package com.example.khulood.nmlibrary;

public class BookIssued {
    private int No;
    private String BookTitle;
    private String Author;
    private String IssueDate;
    private String ReturnDate;
    private String ReturnedAt;

    public BookIssued(){}

    public int getNo() {
        return No;
    }

    public void setNo(int no) {
        No = no;
    }

    public BookIssued(int no, String booktitle, String author, String issuedate, String returndate, String returnedat ) {
        No = no;
        BookTitle = booktitle;
        Author = author;
        IssueDate = issuedate;
        ReturnDate = returndate;
        ReturnedAt = returnedat;
    }

    public String getBookTitle() {
        return BookTitle;
    }

    public void setBookTitle(String bookTitle) {
        BookTitle = bookTitle;
    }

    public String getAuthor() {
        return Author;
    }

    public void setAuthor(String author) {
        Author = author;
    }

    public String getIssueDate() {
        return IssueDate;
    }

    public void setIssueDate(String issueDate) {
        IssueDate = issueDate;
    }

    public String getReturnDate() {
        return ReturnDate;
    }

    public void setReturnDate(String returnDate) {
        ReturnDate = returnDate;
    }

    public String getReturnedAt() {
        return ReturnedAt;
    }

    public void setReturnedAt(String returnedAt) {
        ReturnedAt = returnedAt;
    }
}
