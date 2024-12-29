package com.pawstime.pawstime.domain.board.enums;


public enum BoardType {
    GENERAL(true, false),  // 일반 게시판: 댓글 허용, 신고 허용 X
    INFO(false, false);    // 정보 게시판: 댓글 허용 X, 신고 허용 X

    private final boolean allowComments; // 댓글 허용 여부
    private final boolean allowReports;  // 신고 허용 여부

    BoardType(boolean allowComments, boolean allowReports) {
        this.allowComments = allowComments;
        this.allowReports = allowReports;
    }

    // 댓글 허용 여부 가져오기
    public boolean isAllowComments() {
        return allowComments;
    }

    // 신고 허용 여부 가져오기
    public boolean isAllowReports() {
        return allowReports;
    }
}
