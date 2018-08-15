/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shiva_office;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


/**
 *
 * @author student
 */
public class Shiva_Office {
    Connection con;

    public Shiva_Office() throws ClassNotFoundException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con=DriverManager.getConnection("jdbc:mysql://localhost:3306/shiva_office", "root", "root");
        } catch (SQLException ex) {
            System.out.println(ex);
//            Logger.getLogger(Shiva_Office.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    /**
     * @param args the command line arguments
     */
    
    public static void main(String[] args) {
        // TODO code application logic here
    }

    public int addUser(String s, String name, int age, String phone, String email, String place, String course) {
        int a=0,b=0,d=0,e=0,id=0;
        try {         
            String str1="insert into Login values(0,?,?,?)";
            PreparedStatement s1=con.prepareStatement(str1);
            s1.setString(1, s);
            s1.setString(2, email);
            s1.setString(3, phone);
            a=s1.executeUpdate();
            String str2="select * from Login where l_username=?";
            PreparedStatement s2=con.prepareStatement(str2);
            s2.setString(1, email);
            ResultSet rs = s2.executeQuery();
            while(rs.next()){
                id=rs.getInt(1);
            }
            if(s.equals("HR")){
                String str3="insert into HR values(?,?,?,?,?,?)";
                PreparedStatement s3=con.prepareStatement(str3);
                s3.setInt(1, id);
                s3.setString(2, name);
                s3.setInt(3, age);
                s3.setString(4, phone);
                s3.setString(5, email);
                s3.setString(6, place);
                b=s3.executeUpdate();
            }
            else if(s.equals("Trainer")){
                String str4="insert into Trainer values(?,?,?,?,?,?,?)";
                PreparedStatement s4=con.prepareStatement(str4);
                s4.setInt(1, id);
                s4.setString(2, name);
                s4.setInt(3, age);
                s4.setString(4, phone);
                s4.setString(5, email);
                s4.setString(6, place);
                s4.setString(7, course);
                d=s4.executeUpdate();
            }
            else{
                String str4="insert into Student values(?,?,?,?,?,?,?)";
                PreparedStatement s4=con.prepareStatement(str4);
                s4.setInt(1, id);
                s4.setString(2, name);
                s4.setInt(3, age);
                s4.setString(4, phone);
                s4.setString(5, email);
                s4.setString(6, place);
                s4.setString(7, course);
                e=s4.executeUpdate();
            }
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        } catch (SQLException ex) {
            System.out.println(ex);
//            Logger.getLogger(Shiva_Office.class.getName()).log(Level.SEVERE, null, ex);
        }
        if((a>0)&&(b>0||d>0||e>0)){
            return a;
        }
        else{
            return 0;
        }
    }

    ResultSet login(String user, String uname, String pass) {
        ResultSet rs=null;
        try {
            String str1="select * from Login where l_user=? and l_username=? and l_password=?";
            PreparedStatement s1=con.prepareStatement(str1);
            s1.setString(1, user);
            s1.setString(2, uname);
            s1.setString(3, pass);
            rs=s1.executeQuery();
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        } catch (SQLException ex) {
            System.out.println(ex);
//            Logger.getLogger(Shiva_Office.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }

    public ResultSet searchUser(String s, String name, String course) {
        ResultSet rs=null;
        try {
            if(s.equals("Trainer")){
                String str1="select * from Trainer where t_name=? and t_course=?";
                PreparedStatement s1=con.prepareStatement(str1);
                s1.setString(1, name);
                s1.setString(2, course);
                rs=s1.executeQuery();
            }
            else{
                String str2="select * from Student where s_name=? and s_course=?";
                PreparedStatement s2=con.prepareStatement(str2);
                s2.setString(1, name);
                s2.setString(2, course);
                rs=s2.executeQuery();
            }
        } catch (SQLException ex) {
            System.out.println(ex);
//                Logger.getLogger(Shiva_Office.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public ResultSet searchUser(String name) {
        ResultSet rs=null;
        try {
            String str1="select * from HR where h_name=?";
            PreparedStatement s1=con.prepareStatement(str1);
            s1.setString(1, name);
            rs=s1.executeQuery();
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        } catch (SQLException ex) {
            System.out.println(ex);
//            Logger.getLogger(Shiva_Office.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }

    public int updateUser(int i, String s, String name, int age, String phone, String email, String place, String course) {
        int a=0;
        try {
            if(s.equals("HR")){
                String str1="update HR set h_name=?,h_age=?,h_ph=?,h_email=?,h_place=? where h_id=?";
                PreparedStatement s1=con.prepareStatement(str1);
                s1.setString(1, name);
                s1.setInt(2, age);
                s1.setString(3, phone);
                s1.setString(4, email);
                s1.setString(5, place);
                s1.setInt(6, i);
                a=s1.executeUpdate();
            }
            else if(s.equals("Trainer")){
                String str2="update Trainer set t_name=?,t_age=?,t_ph=?,t_email=?,t_place=?,t_course=? where t_id=?";
                PreparedStatement s2=con.prepareStatement(str2);
                s2.setString(1, name);
                s2.setInt(2, age);
                s2.setString(3, phone);
                s2.setString(4, email);
                s2.setString(5, place);
                s2.setString(6, course);
                s2.setInt(7, i);
                a=s2.executeUpdate();
            }
            else{
                String str3="update Student set s_name=?,s_age=?,s_ph=?,s_email=?,s_place=?,s_course=? where s_id=?";
                PreparedStatement s3=con.prepareStatement(str3);
                s3.setString(1, name);
                s3.setInt(2, age);
                s3.setString(3, phone);
                s3.setString(4, email);
                s3.setString(5, place);
                s3.setString(6, course);
                s3.setInt(7, i);     
                a=s3.executeUpdate();
            }
        } catch (SQLException ex) {
            System.out.println(ex);
//                Logger.getLogger(Shiva_Office.class.getName()).log(Level.SEVERE, null, ex);
        }
        
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        return a;
    }

    public int removeUser(String name) {
        int i=0,a=0,b=0;
        try {
            String str1="select * from HR where h_name=?";
            PreparedStatement s1=con.prepareStatement(str1);
            s1.setString(1, name);
            ResultSet rs = s1.executeQuery();
            while(rs.next()){
                i=rs.getInt(1);
            }
            String str2="delete from HR where h_id=?";
            PreparedStatement s2=con.prepareStatement(str2);
            s2.setInt(1, i);
            a=s2.executeUpdate();
            String str3="delete from Login where l_id=?";
            PreparedStatement s3=con.prepareStatement(str3);
            s3.setInt(1, i);
            b=s3.executeUpdate();
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        } catch (SQLException ex) {
            System.out.println(ex);
//            Logger.getLogger(Shiva_Office.class.getName()).log(Level.SEVERE, null, ex);
        }
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        if(a>0&&b>0){
            return a;
        }
        else{
            return 0;
        }
    }

    public int removeUser(String s, String name, String course) {
        int i=0,a=0,b=0;
        try {
            if(s.equals("Trainer")){
                String str1="select * from Trainer where t_name=? and t_course=?";
                PreparedStatement s1=con.prepareStatement(str1);
                s1.setString(1, name);
                s1.setString(2, course);
                ResultSet rs1 = s1.executeQuery();
                while(rs1.next()){
                    i=rs1.getInt(1);
                }
                String str2="delete from Trainer where t_id=?";
                PreparedStatement s2=con.prepareStatement(str2);
                s2.setInt(1, i);
                a=s2.executeUpdate();
            }
            else{
                String str3="select * from Student where s_name=? and s_course=?";
                PreparedStatement s3=con.prepareStatement(str3);
                s3.setString(1, name);
                s3.setString(2, course);
                ResultSet rs2 = s3.executeQuery();
                while(rs2.next()){
                    i=rs2.getInt(1);
                }
                String str4="delete from Student where s_id=?";
                PreparedStatement s4=con.prepareStatement(str4);
                s4.setInt(1, i);
                a=s4.executeUpdate();
            }
            String str5="delete from Login where l_id=?";
            PreparedStatement s5=con.prepareStatement(str5);
            s5.setInt(1, i);
            b=s5.executeUpdate();
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        } catch (SQLException ex) {
            System.out.println(ex);
//            Logger.getLogger(Shiva_Office.class.getName()).log(Level.SEVERE, null, ex);
        }
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        if(a>0&&b>0){
            return a;
        }
        else{
            return 0;
        }
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public int addCourse(String name, int d, int f) {
        int a=0;
        try {
            String str1="insert into Course values(0,?,?,?)";
            PreparedStatement s1=con.prepareStatement(str1);
            s1.setString(1, name);
            s1.setInt(2, d);
            s1.setInt(3, f);
            a=s1.executeUpdate();
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        } catch (SQLException ex) {
            System.out.println(ex);
//            Logger.getLogger(Shiva_Office.class.getName()).log(Level.SEVERE, null, ex);
        }
        return a;
    }

    public int removeCourse(String name) {
        int a=0;
        try {
            String str1="delete from Course where c_name=?";
            PreparedStatement s1=con.prepareStatement(str1);
            s1.setString(1, name);
            a=s1.executeUpdate();
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        } catch (SQLException ex) {
            System.out.println(ex);
//            Logger.getLogger(Shiva_Office.class.getName()).log(Level.SEVERE, null, ex);
        }
        return a;
    }

    public ResultSet searchCourse(String name) {
        ResultSet rs=null;
        try {
            String str1="select * from Course where c_name=?";
            PreparedStatement s1=con.prepareStatement(str1);
            s1.setString(1, name);
            rs=s1.executeQuery();
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        } catch (SQLException ex) {
            System.out.println(ex);
//            Logger.getLogger(Shiva_Office.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }

    public int updateCourse(int i,String name, int d, int f) {
        int a=0;
        try {
            String str1="update Course set c_name=?,c_duration=?,c_fees=? where c_id=?";
            PreparedStatement s1=con.prepareStatement(str1);
            s1.setString(1, name);
            s1.setInt(2, d);
            s1.setInt(3, f);
            s1.setInt(4, i);
            a=s1.executeUpdate();
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        } catch (SQLException ex) {
            System.out.println(ex);
//            Logger.getLogger(Shiva_Office.class.getName()).log(Level.SEVERE, null, ex);
        }
        return a;
    }

    public ResultSet viewCourse() {
        ResultSet rs=null;
        try {
            String str1="select * from Course";
            PreparedStatement s1=con.prepareStatement(str1);
            rs=s1.executeQuery();
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        } catch (SQLException ex) {
            System.out.println(ex);
//            Logger.getLogger(Shiva_Office.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }

    public ResultSet viewUser(String s) {
        ResultSet rs=null;
        try {
            if(s.equals("HR")){
                String str1="select * from HR";
                PreparedStatement s1=con.prepareStatement(str1);
                rs=s1.executeQuery();
            }
            else if(s.equals("Trainer")){
                String str2="select * from Trainer";
                PreparedStatement s2=con.prepareStatement(str2);
                rs=s2.executeQuery();
            }
            else{
                String str3="select * from Student";
                PreparedStatement s3=con.prepareStatement(str3);
                rs=s3.executeQuery();
            }
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        } catch (SQLException ex) {
            System.out.println(ex);
//            Logger.getLogger(Shiva_Office.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }

    public ResultSet searchCourse() {
        ResultSet rs=null;
        try {
            String str1="select c_name from Course";
            PreparedStatement s1=con.prepareStatement(str1);
            rs=s1.executeQuery();
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        } catch (SQLException ex) {
            System.out.println(ex);
//            Logger.getLogger(Shiva_Office.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }

    public int addExam(String name, String s, String date, String start, String stop, int mark) {
        int a=0,b=0,eid=0;
        try {
            String str1="insert into Exam values(0,?,?,?,?,?,?)";
            PreparedStatement s1=con.prepareStatement(str1);
            s1.setString(1, name);
            s1.setString(2, s);
            s1.setString(3, date);
            s1.setString(4, start);
            s1.setString(5, stop);
            s1.setInt(6, mark);
            a=s1.executeUpdate();
            String str2="select e_id from Exam where e_name=? and e_type=?";
            PreparedStatement s2=con.prepareStatement(str2);
            s2.setString(1, name);
            s2.setString(2, s);
            ResultSet rs=s2.executeQuery();
            while(rs.next()){
                eid=rs.getInt(1);
            }
            String str3="insert into MarkList values(?,0)";
            PreparedStatement s3=con.prepareStatement(str3);
            s3.setInt(1, eid);
            b=s3.executeUpdate();
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        } catch (SQLException ex) {
            System.out.println(ex);
//            Logger.getLogger(Shiva_Office.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(a>0&&b>0){
            return a;
        }
        else{
            return 0;
        }
    }

    public ResultSet searchExam(String name, String s) {
        ResultSet rs=null;
        try {
            String str1="select * from Exam where e_name=? and e_type=?";
            PreparedStatement s1=con.prepareStatement(str1);
            s1.setString(1, name);
            s1.setString(2, s);
            rs=s1.executeQuery();
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        } catch (SQLException ex) {
            System.out.println(ex);
//            Logger.getLogger(Shiva_Office.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }

    public int updateExam(int i, String date, String start, String stop, int mark) {
        int a=0;
        try {
            String str1="update Exam set e_date=?,e_start=?,e_stop=?,e_totmark=? where e_id=?";
            PreparedStatement s1=con.prepareStatement(str1);
            s1.setString(1, date);
            s1.setString(2, start);
            s1.setString(3, stop);
            s1.setInt(4, mark);
            s1.setInt(5, i);
            a=s1.executeUpdate();
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        } catch (SQLException ex) {
            System.out.println(ex);
//            Logger.getLogger(Shiva_Office.class.getName()).log(Level.SEVERE, null, ex);
        }
        return a;
    }

    public int removeExam(String name, String s) {
        int a=0;
        try {
            String str1="delete from Exam where e_name=? and e_type=?";
            PreparedStatement s1=con.prepareStatement(str1);
            s1.setString(1, name);
            s1.setString(2, s);
            a=s1.executeUpdate();
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        } catch (SQLException ex) {
            System.out.println(ex);
//            Logger.getLogger(Shiva_Office.class.getName()).log(Level.SEVERE, null, ex);
        }
        return a;
    }

    public ResultSet viewExam() {
        ResultSet rs=null;
        try {
            String str1="select * from Exam";
            PreparedStatement s1=con.prepareStatement(str1);
            rs=s1.executeQuery();
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        } catch (SQLException ex) {
            System.out.println(ex);
//            Logger.getLogger(Shiva_Office.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }

    public int addMark(String ename, String s, String sname, int m) {
        int a=0,eid=0,mid=0,sid=0;
        try {
            String str1="select e_id from Exam where e_name=? and e_type=?";
            PreparedStatement s1=con.prepareStatement(str1);
            s1.setString(1, ename);
            s1.setString(2, s);
            ResultSet rs1=s1.executeQuery();
            while(rs1.next()){
                eid=rs1.getInt(1);
            }
            String str2="select ml_id from MarkList where mle_id=?";
            PreparedStatement s2=con.prepareStatement(str2);
            s2.setInt(1, eid);
            ResultSet rs2=s2.executeQuery();
            while(rs2.next()){
                mid=rs2.getInt(1);
            }
            String str3="select s_id from Student where s_name=? and s_course=?";
            PreparedStatement s3=con.prepareStatement(str3);
            s3.setString(1, sname);
            s3.setString(2, ename);
            ResultSet rs3=s3.executeQuery();
            while(rs3.next()){
                sid=rs3.getInt(1);
            }
            String str4="insert into Mark values(?,?,?,?)";
            PreparedStatement s4=con.prepareStatement(str4);
            s4.setInt(1, mid);
            s4.setInt(2, sid);
            s4.setString(3, sname);
            s4.setInt(4, m);
            a=s4.executeUpdate();
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        } catch (SQLException ex) {
            System.out.println(ex);
//            Logger.getLogger(Shiva_Office.class.getName()).log(Level.SEVERE, null, ex);
        }
        return a;
    }

    public ResultSet searchMark(String ename, String s, String sname) {
        ResultSet rs3=null;
        int eid=0,mid=0;
        try {
            String str1="select e_id from Exam where e_name=? and e_type=?";
            PreparedStatement s1=con.prepareStatement(str1);
            s1.setString(1, ename);
            s1.setString(2, s);
            ResultSet rs1=s1.executeQuery();
            while(rs1.next()){
                eid=rs1.getInt(1);
            }
            String str2="select ml_id from MarkList where mle_id=?";
            PreparedStatement s2=con.prepareStatement(str2);
            s2.setInt(1, eid);
            ResultSet rs2=s2.executeQuery();
            while(rs2.next()){
                mid=rs2.getInt(1);
            }
            String str3="select * from Mark where m_mlid=? and m_sname=?";
            PreparedStatement s3=con.prepareStatement(str3);
            s3.setInt(1, mid);
            s3.setString(2, sname);
            rs3=s3.executeQuery();
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        } catch (SQLException ex) {
            System.out.println(ex);
//            Logger.getLogger(Shiva_Office.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs3;
    }

    public int updateMark(int mi, int si, int m) {
        int a=0;
        try {
            String str1="update Mark set m_mark=? where m_mlid=? and m_sid=?";
            PreparedStatement s1=con.prepareStatement(str1);
            s1.setInt(1, m);
            s1.setInt(2, mi);
            s1.setInt(3, si);
            a=s1.executeUpdate();
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        } catch (SQLException ex) {
            System.out.println(ex);
//            Logger.getLogger(Shiva_Office.class.getName()).log(Level.SEVERE, null, ex);
        }
        return a;
    }

    public int removeMark(String ename, String s, String sname) {
        int a=0,eid=0,mid=0;
        try {
            String str1="select e_id from Exam where e_name=? and e_type=?";
            PreparedStatement s1=con.prepareStatement(str1);
            s1.setString(1, ename);
            s1.setString(2, s);
            ResultSet rs1=s1.executeQuery();
            while(rs1.next()){
                eid=rs1.getInt(1);
            }
            String str2="select ml_id from MarkList where mle_id=?";
            PreparedStatement s2=con.prepareStatement(str2);
            s2.setInt(1, eid);
            ResultSet rs2=s2.executeQuery();
            while(rs2.next()){
                mid=rs2.getInt(1);
            }
            String str3="delete from Mark where m_mlid=? and m_sname=?";
            PreparedStatement s3=con.prepareStatement(str3);
            s3.setInt(1, mid);
            s3.setString(2, sname);
            a=s3.executeUpdate();
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        } catch (SQLException ex) {
            System.out.println(ex);
//            Logger.getLogger(Shiva_Office.class.getName()).log(Level.SEVERE, null, ex);
        }
        return a;
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public ResultSet viewMark(String ename, String s) {
        int eid=0,mid=0;
        ResultSet rs3=null;
        try {
            String str1="select e_id from Exam where e_name=? and e_type=?";
            PreparedStatement s1=con.prepareStatement(str1);
            s1.setString(1, ename);
            s1.setString(2, s);
            ResultSet rs1=s1.executeQuery();
            while(rs1.next()){
                eid=rs1.getInt(1);
            }   
            String str2="select ml_id from MarkList where mle_id=?";
            PreparedStatement s2=con.prepareStatement(str2);
            s2.setInt(1, eid);
            ResultSet rs2=s2.executeQuery();
            while(rs2.next()){
                mid=rs2.getInt(1);
            }   
            String str3="select m_sid,m_sname,m_mark from Mark where m_mlid=?";
            PreparedStatement s3=con.prepareStatement(str3);
            s3.setInt(1, mid);
            rs3=s3.executeQuery();
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        } catch (SQLException ex) {
            System.out.println(ex);
//            Logger.getLogger(Shiva_Office.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs3;
    }

    public ResultSet viewStudents(int i) {
        String c=null;
        ResultSet rs2=null;
        try {
            String str1="select t_course from Trainer where t_id=?";
            PreparedStatement s1=con.prepareStatement(str1);
            s1.setInt(1, i);
            ResultSet rs1=s1.executeQuery();
            while(rs1.next()){
                c=rs1.getString(1);
            }
            String str2="select * from Student where s_course=?";
            PreparedStatement s2=con.prepareStatement(str2);
            s2.setString(1, c);
            rs2=s2.executeQuery();
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        } catch (SQLException ex) {
            System.out.println(ex);
//            Logger.getLogger(Shiva_Office.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs2;
    }

    public ResultSet viewTExam(int i) {
        String c=null;
        ResultSet rs2=null;
        try {
            String str1="select t_course from Trainer where t_id=?";
            PreparedStatement s1=con.prepareStatement(str1);
            s1.setInt(1, i);
            ResultSet rs1=s1.executeQuery();
            while(rs1.next()){
                c=rs1.getString(1);
            }
            String str2="select * from Exam where e_name=?";
            PreparedStatement s2=con.prepareStatement(str2);
            s2.setString(1, c);
            rs2=s2.executeQuery();
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        } catch (SQLException ex) {
            System.out.println(ex);
//            Logger.getLogger(Shiva_Office.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs2;
    }

    public ResultSet viewTMark(int i, String s) {
        String c=null;
        int e=0,m=0;
        ResultSet rs4=null;
        try {
            String str1="select t_course from Trainer where t_id=?";
            PreparedStatement s1=con.prepareStatement(str1);
            s1.setInt(1, i);
            ResultSet rs1=s1.executeQuery();
            while(rs1.next()){
                c=rs1.getString(1);
            }
            String str2="select e_id from Exam where e_name=? and e_type=?";
            PreparedStatement s2=con.prepareStatement(str2);
            s2.setString(1, c);
            s2.setString(2, s);
            ResultSet rs2=s2.executeQuery();
            while(rs2.next()){
                e=rs2.getInt(1);
            }
            String str3="select ml_id from MarkList where mle_id=?";
            PreparedStatement s3=con.prepareStatement(str3);
            s3.setInt(1, e);
            ResultSet rs3=s3.executeQuery();
            while(rs3.next()){
                m=rs3.getInt(1);
            }
            String str4="select * from Mark where m_mlid=?";
            PreparedStatement s4=con.prepareStatement(str4);
            s4.setInt(1, m);
            rs4=s4.executeQuery();
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        } catch (SQLException ex) {
            System.out.println(ex);
//            Logger.getLogger(Shiva_Office.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs4;
    }

    public ResultSet viewUser(int id) {
        ResultSet rs=null;
        try {
            String str1="select * from Login where l_id=?";
            PreparedStatement s1=con.prepareStatement(str1);
            s1.setInt(1, id);
            rs=s1.executeQuery();
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        } catch (SQLException ex) {
            System.out.println(ex);
//            Logger.getLogger(Shiva_Office.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }

    public int updateUser(int id, String u, String p) {
        int r=0;
        try {
            String str1="update Login set l_username=?,l_password=? where l_id=?";
            PreparedStatement s1=con.prepareStatement(str1);
            s1.setString(1, u);
            s1.setString(2, p);
            s1.setInt(3, id);
            r=s1.executeUpdate();
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        } catch (SQLException ex) {
            System.out.println(ex);
//            Logger.getLogger(Shiva_Office.class.getName()).log(Level.SEVERE, null, ex);
        }
        return r;
    }

    public ResultSet viewSExam(int id) {
        String c=null;
        ResultSet rs2=null;
        try {
            String str1="select s_course from Student where s_id=?";
            PreparedStatement s1=con.prepareStatement(str1);
            s1.setInt(1, id);
            ResultSet rs1=s1.executeQuery();
            while(rs1.next()){
                c=rs1.getString(1);
            }
            String str2="select * from Exam where e_name=?";
            PreparedStatement s2=con.prepareStatement(str2);
            s2.setString(1, c);
            rs2=s2.executeQuery();
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        } catch (SQLException ex) {
            System.out.println(ex);
//            Logger.getLogger(Shiva_Office.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs2;
    }

    public ResultSet viewSMark(int i) {
        ResultSet rs=null;
        try {
            String str1="select Exam.e_type,Mark.m_mark from Exam join Mark where Exam.e_id=Mark.m_mlid and m_sid=?";
            PreparedStatement s1=con.prepareStatement(str1);
            s1.setInt(1, i);
            rs=s1.executeQuery();
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        } catch (SQLException ex) {
            System.out.println(ex);
//            Logger.getLogger(Shiva_Office.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }

}
