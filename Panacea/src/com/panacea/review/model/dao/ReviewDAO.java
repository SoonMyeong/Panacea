package com.panacea.review.model.dao;

import static com.panacea.common.JDBCTemplate.close;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

import com.panacea.review.model.exception.ReviewException;
import com.panacea.review.model.vo.Review;
import com.panacea.review.model.vo.ReviewComment;



public class ReviewDAO {
	private Properties prop = new Properties();
	
	
	public ReviewDAO() {
		try {
			//클래스객체 위치찾기 : 절대경로를 반환한다. 
			// "/" : 루트디렉토리를 절대경로로 URL객체로 반환한다.
			// "./" : 현재디렉토리를 절대경로로 URL객체로 반환한다.
			// "./query.properties : 현재경로의 query.properties파일의 경로를 URL객체로 반환함.
			String fileName = ReviewDAO.class.getResource("/sql/review/review-query.properties").getPath();
			prop.load(new FileReader(fileName));
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}
	
	

	public ArrayList<Review> selectReviewList(Connection conn, int cPage, int numPerPage) throws ReviewException {
		ArrayList <Review> list = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = prop.getProperty("selectReviewList");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, (cPage-1)*numPerPage+1);
			pstmt.setInt(2, cPage*numPerPage);
			
			rset = pstmt.executeQuery();
			
			list = new ArrayList<>();
			while(rset.next()) {
				Review r= new Review();
				r.setReviewNo(rset.getInt("review_no"));
				r.setPatientId(rset.getString("patient_id"));
				r.setReviewTitle(rset.getString("review_title"));
				r.setReviewContent(rset.getString("review_content"));
				r.setGrade(rset.getInt("grade"));
				r.setHits(rset.getInt("hits"));
				r.setReviewDate(rset.getDate("review_date"));
				list.add(r);
			}
			System.out.println("list@ReviewDAO = "+ list);
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ReviewException(e.getMessage());
		} finally {
			close(rset);
			close(pstmt);
			
		}
			
		return list;
	}



	public int selectReviewCount(Connection conn) throws ReviewException {
		int totalReviewCount = 0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = prop.getProperty("selectReviewCount");
		
		try {
			pstmt = conn.prepareStatement(query);
			rset = pstmt.executeQuery();
			rset.next();
			totalReviewCount = rset.getInt("cnt");
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ReviewException(e.getMessage());
		}finally {
			close(rset);
			close(pstmt);
		}
	
		return totalReviewCount;
	}



	public Review selectReviewView(Connection conn, int reviewNo) throws ReviewException {
		System.out.println("reviewNo@DAO = "+reviewNo);
		Review review = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = prop.getProperty("selectReviewView");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, reviewNo);
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
			review = new Review();
			review.setReviewNo(rset.getInt("review_no"));
			review.setReviewTitle(rset.getString("review_title"));
			review.setReviewContent(rset.getString("review_content"));
			review.setPatientId(rset.getString("patient_id"));
			review.setGrade(rset.getInt("grade"));
			review.setHits(rset.getInt("hits"));
			review.setReviewDate(rset.getDate("review_date"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ReviewException(e.getMessage());
		} finally {
			close(rset);
			close(pstmt);
		}
		return review;
	}



	public int insertReview(Connection conn, Review review) throws ReviewException {
		int result = 0;
		PreparedStatement pstmt = null;
		System.out.println(review.getReviewTitle());
		System.out.println(review.getPatientId());
		System.out.println(review.getGrade());
		System.out.println(review.getReviewContent());
		
		String query = prop.getProperty("insertReview");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, review.getPatientId());
			pstmt.setString(2, review.getReviewTitle());
			pstmt.setString(3, review.getReviewContent());
			pstmt.setInt(4, review.getGrade());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ReviewException(e.getMessage());
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public int updateReview(Connection conn, Review review) throws ReviewException {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = prop.getProperty("updateReview");
		
		System.out.println(review.getReviewTitle());
		System.out.println(review.getReviewContent());
		System.out.println(review.getGrade());
		System.out.println(review.getReviewNo());
		
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, review.getReviewTitle());
			pstmt.setString(2, review.getReviewContent());
			pstmt.setInt(3, review.getGrade());
			pstmt.setInt(4, review.getReviewNo());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ReviewException(e.getMessage());
		}finally {
			close(pstmt);
		}
		
		return result;
	}



	public int reviewDelete(Connection conn, int reviewNo) throws ReviewException {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = prop.getProperty("deleteReview");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, reviewNo);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ReviewException(e.getMessage());
		}finally {
			close(pstmt);
		}
		
		return result;
	}



	public int insertReviewComment(Connection conn, ReviewComment rc) throws ReviewException {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = prop.getProperty("insertComment");
		
		try {
			int reviewCommentRef =  rc.getCommentRef()==0?0:rc.getCommentRef();
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, rc.getReviewNo());
			pstmt.setString(2, rc.getCommentWriter());
			pstmt.setInt(3, rc.getCommentLevel());
			pstmt.setInt(4, reviewCommentRef);
			pstmt.setString(5, rc.getCommentContent());
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ReviewException(e.getMessage());
		} finally {
			close(pstmt);
		}
		
		
		return result;
	}



	public int deleteComment(Connection conn, int commentNo) throws ReviewException {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = prop.getProperty("deleteComment");
		
		try {
			pstmt=conn.prepareStatement(query);
			pstmt.setInt(1, commentNo);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ReviewException(e.getMessage());
		}finally {
			close(conn);
		}
		return result;
	}



	public int increaseReadCount(Connection conn, int reviewNo) throws ReviewException {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = prop.getProperty("increaseReadCount");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, reviewNo);
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ReviewException(e.getMessage());
		} finally {
			close(pstmt);
		}
		
		
		return result;
		
	}



	public ArrayList<Review> selectByTitle(Connection conn, String srchKeyword, int cPage, int numPerPage) throws ReviewException {
		ArrayList <Review> list = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = prop.getProperty("selectByTitle");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, "%"+srchKeyword+"%");
			pstmt.setInt(2, (cPage-1)*numPerPage+1);
			pstmt.setInt(3, cPage*numPerPage);
			
			rset = pstmt.executeQuery();
			
			list = new ArrayList<>();
			while(rset.next()) {
				Review r= new Review();
				r.setReviewNo(rset.getInt("review_no"));
				r.setPatientId(rset.getString("patient_id"));
				r.setReviewTitle(rset.getString("review_title"));
				r.setReviewContent(rset.getString("review_content"));
				r.setGrade(rset.getInt("grade"));
				r.setHits(rset.getInt("hits"));
				r.setReviewDate(rset.getDate("review_date"));
				list.add(r);
			}
			System.out.println("list@ReviewDAO = "+ list);
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ReviewException(e.getMessage());
		} finally {
			close(rset);
			close(pstmt);
			
		}
		return list;
		
	}



	public ArrayList<Review> selectById(Connection conn, String srchKeyword, int cPage, int numPerPage) throws ReviewException {
		ArrayList <Review> list = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = prop.getProperty("selectById");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, "%"+srchKeyword+"%");
			pstmt.setInt(2, (cPage-1)*numPerPage+1);
			pstmt.setInt(3, cPage*numPerPage);
			
			rset = pstmt.executeQuery();
			
			list = new ArrayList<>();
			while(rset.next()) {
				Review r= new Review();
				r.setReviewNo(rset.getInt("review_no"));
				r.setPatientId(rset.getString("patient_id"));
				r.setReviewTitle(rset.getString("review_title"));
				r.setReviewContent(rset.getString("review_content"));
				r.setGrade(rset.getInt("grade"));
				r.setHits(rset.getInt("hits"));
				r.setReviewDate(rset.getDate("review_date"));
				list.add(r);
			}
			System.out.println("list@ReviewDAO = "+ list);
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ReviewException(e.getMessage());
		} finally {
			close(rset);
			close(pstmt);
			
		}
		return list;
	}



	public ArrayList<Review> selectByDate(Connection conn, String srchMon, int cPage, int numPerPage) throws ReviewException {
		ArrayList <Review> list = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = prop.getProperty("selectByDate");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, srchMon);
			pstmt.setInt(2, (cPage-1)*numPerPage+1);
			pstmt.setInt(3, cPage*numPerPage);
			
			rset = pstmt.executeQuery();
			
			list = new ArrayList<>();
			while(rset.next()) {
				Review r= new Review();
				r.setReviewNo(rset.getInt("review_no"));
				r.setPatientId(rset.getString("patient_id"));
				r.setReviewTitle(rset.getString("review_title"));
				r.setReviewContent(rset.getString("review_content"));
				r.setGrade(rset.getInt("grade"));
				r.setHits(rset.getInt("hits"));
				r.setReviewDate(rset.getDate("review_date"));
				list.add(r);
			}
			System.out.println("list@ReviewDAO = "+ list);
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ReviewException(e.getMessage());
		} finally {
			close(rset);
			close(pstmt);
			
		}
		return list;
	}



	public int selectReviewCountByTitle(Connection conn, String srchKeyword) throws ReviewException {
		int totalReviewCount = 0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = prop.getProperty("selectReviewCountByTitle");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, "%"+srchKeyword+"%");
			rset = pstmt.executeQuery();
			rset.next();
			totalReviewCount = rset.getInt("cnt");
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ReviewException(e.getMessage());
		}finally {
			close(rset);
			close(pstmt);
		}
	
		return totalReviewCount;
	}



	public int selectReviewCountById(Connection conn, String srchKeyword) throws ReviewException {
		int totalReviewCount = 0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = prop.getProperty("selectReviewCountById");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, "%"+srchKeyword+"%");
			rset = pstmt.executeQuery();
			rset.next();
			totalReviewCount = rset.getInt("cnt");
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ReviewException(e.getMessage());
		}finally {
			close(rset);
			close(pstmt);
		}
	
		return totalReviewCount;

	}



	public int selectReviewCountByDate(Connection conn, String srchMon) throws ReviewException {
		int totalReviewCount = 0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = prop.getProperty("selectReviewCountByDate");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, srchMon);
			rset = pstmt.executeQuery();
			rset.next();
			totalReviewCount = rset.getInt("cnt");
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ReviewException(e.getMessage());
		}finally {
			close(rset);
			close(pstmt);
		}
	
		return totalReviewCount;
	}



	public int updateLike(Connection conn, int commentNo) throws ReviewException {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = prop.getProperty("updateLike");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, commentNo);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ReviewException(e.getMessage());
		}finally {
			close(pstmt);
		}
		
		return result;
	}



	public int updateBad(Connection conn, int commentNo) throws ReviewException {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = prop.getProperty("updateBad");
		System.out.println("commentNo@DAO="+ commentNo);
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, commentNo);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ReviewException(e.getMessage());
		}finally {
			close(pstmt);
		}
		
		return result;
	}



	public ArrayList<ReviewComment> selectMostLikeComment(Connection conn, int reviewNo) throws ReviewException {
		ArrayList<ReviewComment> mostLikeCommentList = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = prop.getProperty("selectMostLikeComment");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, reviewNo);
			rset = pstmt.executeQuery();
			
			mostLikeCommentList = new ArrayList<>();
			while(rset.next()) {
				ReviewComment rc = new ReviewComment();
				rc.setCommentNo(rset.getInt("comment_no"));
				rc.setReviewNo(rset.getInt("review_no"));
				rc.setCommentWriter(rset.getString("comment_writer"));
				rc.setCommentLevel(rset.getInt("comment_level"));
				rc.setCommentRef(rset.getInt("comment_ref"));
				rc.setCommentContent(rset.getString("comment_content"));
				rc.setCommentDate(rset.getDate("comment_date"));
				rc.setLikeIt(rset.getInt("likeIt"));
				rc.setBad(rset.getInt("bad"));
				
				mostLikeCommentList.add(rc);
				
			}
			System.out.println("mostLikeCommentList@DAO = "+mostLikeCommentList);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ReviewException(e.getMessage());
		}finally {
			close(rset);
			close(pstmt);
		}
		
		
		return mostLikeCommentList;
	}


	public int updateRefComment(Connection conn, int commentNo) throws ReviewException {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = prop.getProperty("updateRefComment");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, commentNo);
			result = pstmt.executeUpdate();
			System.out.println("result@RefDAo = "+ result);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ReviewException(e.getMessage());
		}finally {
			close(pstmt);
		}
		
		return result;
	}

   public int deleteRefComment(Connection conn, int commentNo) throws ReviewException {
	      int result = 0;
	      PreparedStatement pstmt = null;
	      String query = prop.getProperty("deleteRefComment");
	      
	      try {
	         pstmt = conn.prepareStatement(query);
	         pstmt.setInt(1, commentNo);
	         result = pstmt.executeUpdate();
	         System.out.println("result@RefDAo = "+ result);
	      } catch (SQLException e) {
	         e.printStackTrace();
	         throw new ReviewException(e.getMessage());
	      }finally {
	         close(pstmt);
	      }
	      
	      return result;
	   }

   
   public ArrayList<ReviewComment> selectReviewComment(Connection conn, int reviewNo, int cPage, int numPerPage) throws ReviewException {
	      ArrayList<ReviewComment> list = null;
	      PreparedStatement pstmt = null;
	      ResultSet rset = null;
	      String query = prop.getProperty("selectReviewComment");
	      
	      try {
	         pstmt = conn.prepareStatement(query);
	         pstmt.setInt(1, reviewNo);
	         pstmt.setInt(2, (cPage-1)*numPerPage+1);
	         pstmt.setInt(3, cPage*numPerPage);
	         rset = pstmt.executeQuery();
	         list = new ArrayList<>();
	         while(rset.next()) {
	            ReviewComment rc = new ReviewComment();
	            rc.setCommentNo(rset.getInt("comment_no"));
	            rc.setReviewNo(rset.getInt("review_no"));
	            rc.setCommentWriter(rset.getString("comment_writer"));
	            rc.setCommentLevel(rset.getInt("comment_level"));
	            rc.setCommentRef(rset.getInt("comment_ref"));
	            rc.setCommentContent(rset.getString("comment_content"));
	            rc.setCommentDate(rset.getDate("comment_date"));
	            rc.setLikeIt(rset.getInt("likeIt"));
	            rc.setBad(rset.getInt("bad"));
	            
	            
	            list.add(rc);
	            
	         }
	         System.out.println("list@commentDAO"+ list);
	      } catch (SQLException e) {
	         e.printStackTrace();
	         throw new ReviewException(e.getMessage());
	      } finally {
	         close(rset);
	         close(pstmt);
	      }
	      return list;
	   }
   
   
   	public int selectCommentCnt(Connection conn, int reviewNo) throws ReviewException {
	      int totalCommentCnt = 0;
	      PreparedStatement pstmt = null;
	      ResultSet rset = null;
	      String query = prop.getProperty("selectCommentCount");
	      
	      try {
	         pstmt= conn.prepareStatement(query);
	         pstmt.setInt(1, reviewNo);
	         rset = pstmt.executeQuery();
	         rset.next();
	         totalCommentCnt = rset.getInt("cnt");
	      } catch (SQLException e) {
	         e.printStackTrace();
	         throw new ReviewException(e.getMessage());
	      } finally {
	         close(rset);
	         close(pstmt);
	      }
	      
	      
	      return totalCommentCnt;
	   }
   	
   	
    public int selectRefCommentCnt(Connection conn, int commentNo) throws ReviewException {
        int cnt = 0;
        PreparedStatement pstmt = null;
        ResultSet rset = null;
        String query = prop.getProperty("selectRefCommentCnt");
        
        try {
           pstmt= conn.prepareStatement(query);
           pstmt.setInt(1, commentNo);
           rset = pstmt.executeQuery();
           rset.next();
           cnt = rset.getInt("cnt");
        } catch (SQLException e) {
	         e.printStackTrace();
	         throw new ReviewException(e.getMessage());
        } finally {
           close(rset);
           close(pstmt);
        }
        
        
        return cnt;
  }
   
}
