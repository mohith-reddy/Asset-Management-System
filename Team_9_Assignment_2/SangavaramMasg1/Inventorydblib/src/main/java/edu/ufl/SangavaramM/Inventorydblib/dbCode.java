package edu.ufl.SangavaramM.Inventorydblib;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class dbCode {

    private static String dbname;
    private static Connection mcn;

    static {
        dbname = "eqpinventory";
    }

    public dbCode() {
        setConneciton("ism6236", "ism6236bo");
    }

    public void setConneciton(String uid, String password) {
        try {
            String connectionUrl = String.format("jdbc:mysql://localhost:3306/%s?user=%s&password=%s&useSSL=false", dbname, uid, password);
            mcn = DriverManager.getConnection(connectionUrl);
            if (mcn == null) {
                System.out.println("Connection Failed");
            }
        } catch (SQLException sqlex) {
            sqlex.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private int TransactSQL(String[] sql) {
        Statement st = null;
        int n = 0;
        try {
            st = mcn.createStatement();
            //System.out.println(mcn.getAutoCommit());
            mcn.setAutoCommit(false);

            for (int i = 0; i < sql.length; i++) {
                n += st.executeUpdate(sql[i]);
            }
            //mcn.rollback();
            mcn.commit();
        } catch (SQLException ex) {
            try {
                //rollback if there is an error
                mcn.rollback();
                ex.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (st != null) {
                    st.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return n;
    }

    public String getEmployeeName(String id) {
        Statement s = null;
        String name = "";
        try {
            s = mcn.createStatement();

            String sql = String.format("Select name from employee  WHERE eid = %s", id);
            ResultSet rs = s.executeQuery(sql);
            while (rs.next()) {
                name = rs.getString(1);

            }
        } catch (Exception e) {

            e.printStackTrace();

        }

        return name;


    }


    public List<String> listInventory(String eid) {
        List<String> retList = new ArrayList<String>();
        Statement s = null;
        String row = "";
        try {
            s = mcn.createStatement();

            String sql = String.format("select name, oid, eqid, datein, dateout from employee e, assignment a where e.eid = a.eid and e.eid = %s;", eid);
            ResultSet rs = s.executeQuery(sql);
            while (rs.next()) {

                String name = rs.getString(1);
                String oid = rs.getString(2);
                String eqid = rs.getString(3);
                String datein = String.valueOf(rs.getDate(4));
                String dateout = String.valueOf(rs.getDate(5));
                row = String.format("%s,%s,%s,%s,%s", name, oid, eqid, datein, dateout);
                retList.add(row);


            }


        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (s != null && !s.isClosed()) s.close();
            } catch (SQLException sqle) {
                sqle.printStackTrace();
            }
        }

        return retList;

    }

    public String assignEquipment(String eid, String oid, String eqid) {


        Statement s = null;


        int tranCount = 0;
        String empName = "";

        try {
            s = mcn.createStatement();
            String sql = String.format("insert into assignment(eid,oid,eqid,datein) values ('%s','%s',%s,'%s');", eid, oid, eqid, LocalDate.now());
            tranCount += s.executeUpdate(sql);
            String queryForName = String.format("select name from employee where eid = '%s'", eid);
            ResultSet rs = s.executeQuery(queryForName);
            while (rs.next())
                empName = rs.getString(1);

            s.close();


        } catch (SQLException sqlex) {
            sqlex.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (tranCount == 1) {


            return String.format("Equipment %s is assigned to %s in office %s on %s", eqid, empName, oid, LocalDate.now());
        } else {
            return String.format("Cannot assign");
        }


    }

    public String reassignEquipment(String eid, String oid, String eqid) {
        Statement s = null;
        String empNameBefore = "";
        String offIdBefore = "";
        String empNameAfter = "";
        String offIdAfter = "";
        int transCount = 0;
        try {
            s = mcn.createStatement();
            String[] sql = new String[2];
            sql[0] = String.format("update assignment set dateout = '%s' where  eqid = %s;", LocalDate.now(), eqid);
            sql[1] = String.format("insert into assignment(eid,oid,eqid,datein) values ('%s','%s',%s,'%s');", eid, oid, eqid, LocalDate.now());
            String empNameBeforeQ = String.format(" select e.name, a.oid from employee e, assignment a where a.dateout is null and  a.eqid = %s and a.eid = e.eid;", eqid);
            ResultSet rs = s.executeQuery(empNameBeforeQ);
            while (rs.next()) {
                empNameBefore = rs.getString(1);
                offIdBefore = rs.getString(2);
            }

            transCount = TransactSQL(sql);

            String empNameAfterQ = String.format(" select e.name, a.oid from employee e, assignment a where a.dateout is null and  a.eqid = %s and a.eid = e.eid;", eqid);
            rs = s.executeQuery(empNameAfterQ);
            while (rs.next()) {
                empNameAfter = rs.getString(1);
                offIdAfter = rs.getString(2);
            }

            s.close();


        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (transCount >= 2) {
            return String.format("Equipment %s is removed from office %s occupied by %s and assigned to %s in office %s on %s", eqid, offIdBefore, empNameBefore, empNameAfter, offIdAfter, LocalDate.now());

        } else {
            return "Cannot reassign";
        }


    }
/*
    public static void main(String[] args) {
        dbCode o = new dbCode();
        if(o != null){
            System.out.println("Success");
        }
        else{

            System.out.println("Failed");
        }

        //System.out.println(o.getEmployeeName("2222"));

        List<String> empAssigment = o.listInventory("1111");
        for (int i = 0; i < empAssigment.size(); i++) {
            System.out.println(empAssigment.get(i));
        }

        //System.out.println(o.assignEquipment("2222", "stz333", "3"));

       //System.out.println(o.reassignEquipment("3333", "STZ362", "3"));


    }

*/
}
