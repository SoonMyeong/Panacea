package com.panacea.review.model.service;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import static com.panacea.common.JDBCTemplate.*;

import com.panacea.review.model.dao.ReviewDAO;
import com.panacea.review.model.exception.ReviewException;
import com.panacea.review.model.vo.Review;
import com.panacea.review.model.vo.ReviewComment;

public class ReviewService {

	public List<Review> selectReviewList(int cPage, int numPerPage) throws ReviewException {
		Connection conn = getConnection();
		ArrayList <Review> list = new ReviewDAO().selectReviewList(conn, cPage, numPerPage);
		close(conn);
		return list;
	}

	public int selectReviewCount() throws ReviewException {
		Connection conn = getConnection();
		int totalReviewCount = new ReviewDAO().selectReviewCount(conn);
		close(conn);
		return totalReviewCount;
	}



	public Review selectReviewView(int reviewNo) throws ReviewException {
		Connection conn = getConnection();
		Review review = new ReviewDAO().selectReviewView(conn, reviewNo);
		close(conn);
		return review;
	}

	public int insertReview(Review review) throws ReviewException {
		Connection conn = getConnection();
		int result = new ReviewDAO().insertReview(conn, review);
		if(result>0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		close(conn);
		return result;
	}

	public int updateReview(Review review) throws ReviewException {
		Connection conn = getConnection();
		int result = new ReviewDAO().updateReview(conn, review);
		if(result>0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		close(conn);
		return result;
	}

	public int reiewDelete(int reviewNo) throws ReviewException {
		Connection conn = getConnection();
		int result = new ReviewDAO().reviewDelete(conn, reviewNo);
		if(result>0) {
			commit(conn);
			
		}else {
			rollback(conn);
		}
		close(conn);
		return result;
	}

	public int insertReviewComment(ReviewComment rc) throws ReviewException {
		Connection conn = getConnection();
		int result = new ReviewDAO().insertReviewComment(conn, rc);
		if(result>0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		close(conn);
		return result;
	}

	public int deleteComment(int commentNo) throws ReviewException {
		Connection conn = getConnection();
		int commentNo1 = commentNo;
		//자식댓글 먼저 삭제 시키고
		int result =  new ReviewDAO().deleteRefComment(conn, commentNo1);
		System.out.println("result@Service"+ result);
		if(result>0) {
			//부모댓글 삭제?? 왜이런지는 모르겠음
			result = new ReviewDAO().deleteComment(conn, commentNo1);
			commit(conn);
		}else {
			rollback(conn);
		}
		close(conn);
		return result;
	}

	public void increaseReadCount(int reviewNo) throws ReviewException {
		Connection conn = getConnection();
	   int result = new ReviewDAO().increaseReadCount(conn, reviewNo);
	   if(result>0) {
		   commit(conn);
	   }else {
		   rollback(conn);
	   }
	   close(conn);
		
	}

	public ArrayList<Review> selectByTitle(String srchKeyword, int cPage, int numPerPage) throws ReviewException {
		Connection conn = getConnection();
		ArrayList<Review> list = new ReviewDAO().selectByTitle(conn, srchKeyword, cPage, numPerPage);
		close(conn);
		return list;
	}

	public ArrayList<Review> selectById(String srchKeyword, int cPage, int numPerPage) throws ReviewException {
		Connection conn = getConnection();
		ArrayList<Review> list = new ReviewDAO().selectById(conn, srchKeyword, cPage, numPerPage);
		close(conn);
		return list;
	}

	public ArrayList<Review> selectByDate(String srchMon, int cPage, int numPerPage) throws ReviewException {
		Connection conn = getConnection();
		ArrayList<Review> list = new ReviewDAO().selectByDate(conn, srchMon, cPage, numPerPage);
		close(conn);
		return list;
	}

	public int selectReviewCountByTitle(String srchKeyword) throws ReviewException {
		Connection conn = getConnection();
		int totalReviewCount = new ReviewDAO().selectReviewCountByTitle(conn ,srchKeyword);
		close(conn);
		return totalReviewCount;
	}

	public int selectReviewCountById(String srchKeyword) throws ReviewException {
		Connection conn = getConnection();
		int totalReviewCount = new ReviewDAO().selectReviewCountById(conn ,srchKeyword);
		close(conn);
		return totalReviewCount;	
	}

	public int selectReviewCountByDate(String srchMon) throws ReviewException {
		// TODO Auto-generated method stub
		Connection conn = getConnection();
		int totalReviewCount = new ReviewDAO().selectReviewCountByDate(conn ,srchMon);
		close(conn);
		return totalReviewCount;	
	}

	public int updateLike(int commentNo) throws ReviewException {
		Connection conn = getConnection();
		int result = new ReviewDAO().updateLike(conn, commentNo);
		if(result>0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		close(conn);
		return result;
	}

	public int updateBad(int commentNo) throws ReviewException {
		Connection conn = getConnection();
		int result = new ReviewDAO().updateBad(conn, commentNo);
		if(result>0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		close(conn);
		return result;
	}

	public ArrayList<ReviewComment> selectMostLikeComment(int reviewNo) throws ReviewException {
		Connection conn = getConnection();
		ArrayList<ReviewComment> mostLikeCommentList = new ReviewDAO().selectMostLikeComment(conn, reviewNo);
		close(conn);
		return mostLikeCommentList;
	}

	public int deleteRefComment(int commentNo) throws ReviewException {
		Connection conn = getConnection();
		//DAO  = deleteComment DAO 공유
		int result = new ReviewDAO().deleteComment(conn, commentNo);
		if(result > 0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		close(conn);
		return result;
	}

	public ArrayList<ReviewComment> selectReviewComment(int reviewNo, int cPage, int numPerPage) throws ReviewException {
		  Connection conn = getConnection();
		  ArrayList<ReviewComment> list = new ReviewDAO().selectReviewComment(conn, reviewNo, cPage, numPerPage);
		  close(conn);
		  
		  return list;
	}
	
	public int selectCommentCnt(int reviewNo) throws ReviewException {
      Connection conn = getConnection();
      int totalCommentCnt = new ReviewDAO().selectCommentCnt(conn, reviewNo);
      close(conn);
      return totalCommentCnt;
   }
	
   public int selectRefCommentCnt(int commentNo) throws ReviewException {
      Connection conn = getConnection();
      int cnt = new ReviewDAO().selectRefCommentCnt(conn, commentNo);
      close(conn);
      
      return cnt;
   }
   
   public int deleteCommentNoRef(int commentNo) throws ReviewException {
      Connection conn = getConnection();
      int   result = new ReviewDAO().deleteComment(conn, commentNo);
         System.out.println("result@Service"+ result);
      if(result>0) {
         commit(conn);
      }else {
         rollback(conn);
      }
      close(conn);
      return result;
   }
	

}
