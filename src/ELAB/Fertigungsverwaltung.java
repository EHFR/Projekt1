package ELAB;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

public class Fertigungsverwaltung {
    private ArrayList<Auftrag> auftraege;

    public Fertigungsverwaltung() {
        auftraege = new ArrayList<>();
        this.reloadAuftraege();
    }

    private void reloadAuftraege() {
        Db db = new Db();
        this.auftraege.clear();
        try {
            ResultSet rs = db.exequteQuery("SELECT * FROM Auftrag");
            while (rs.next()) {
                Auftrag a = new Auftrag(rs.getInt("ID"), rs.getString("Titel"),
                        rs.getString("FertigungsArt"), rs.getString("DateiName"),
                        rs.getString("DateiOrt"), rs.getFloat("Kosten"), rs.getBoolean("angenommen"), rs.getTimestamp("statusZeitstempel_angenommen"),
                        rs.getBoolean("gefertigt"),rs.getTimestamp("statusZeitstempel_gefertigt"), rs.getBoolean("kosten_kalkuliert"), rs.getTimestamp("statusZeitstempel_kosten_kalkuliert"),
                        rs.getBoolean("abgeholt"), rs.getTimestamp("statusZeitstempel_abgeholt"), 
                        rs.getBoolean("abgerechnet"), rs.getTimestamp("statusZeitstempel_abgerechnet"), rs.getBoolean("wartenAufMaterial"), rs.getTimestamp("statusZeitstempel_wartenAufMaterial"),
                        rs.getBoolean("fertigungFehlgeschlagen"), rs.getTimestamp("statusZeitstempel_fertigungFehlgeschlagen"));
                a.setZeitstempel(rs.getTimestamp("timestamp"));
                this.auftraege.add(a);
            }
        } catch (SQLException e) {
            System.out.println("Error while reading Database!");
            e.printStackTrace();
        } finally {
            db.close();
        }
    }

    public ArrayList<Auftrag> getAuftraege() {
        this.reloadAuftraege();
        return auftraege;
    }

    public void addAuftrag(String titel, String fertigungsArt, String dateiName, String dateiOrt, float kosten) {

        Db db = new Db();

        String sql = "INSERT INTO Auftrag (Titel, FertigungsArt, DateiName, DateiOrt, Kosten, angenommen, gefertigt, kosten_kalkuliert, abgeholt, abgerechnet, wartenAufMaterial, fertigungFehlgeschlagen) "
                + "VALUES ('" + titel + "','" + fertigungsArt + "','" + dateiName
                + "','" + dateiOrt + "','" + kosten + "'," + false + "," + false + ","
                + false + "," + false + "," + false + "," + false + "," + false + ")";
        try {
            db.updateQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void removeAuftrag(int id) {
        Db db = new Db();
        String sql = "DELETE FROM Auftrag WHERE ID = " + id + " ";

        try {
            db.updateQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateAuftrag(int id, String titel, String fertigungsArt, String dateiName, String dateiOrt, float kosten) {
        Db db = new Db();
        String sql = "UPDATE Auftrag SET Titel = '" + titel + "', FertigungsArt = '" + fertigungsArt
                + "', DateiName = '" + dateiName + "', DateiOrt = '" + dateiOrt + "', Kosten = '" + kosten
                + "WHERE ID = " + id + "";
        try {
            db.updateQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateStatus(Auftrag a) {
        Db db = new Db();
        String sql = "";
        
        Timestamp timestampNew = new Timestamp(System.currentTimeMillis());
        
//        String sq = "UPDATE Auftrag SET angenommen = " + angenommen + ", statusZeitstempel_angenommen " + timestamp + ", gefertigt = " + gefertigt + ", statusZeitstempel_gefertigt " 
//        		+ timestamp + ", kosten_kalkuliert = " + kosten_kalkuliert + ", statusZeitstempel_kosten_kalkuliert " + timestamp + ", abgeholt = "
//                + abgeholt + ", statusZeitstempel_abgeholt " + timestamp + ", abgerechnet = " + abgerechnet + ", statusZeitstempel_abgerechnet " + timestamp + ", wartenAufMaterial = " 
//	      		+ wartenAufMaterial + ", statusZeitstempel_wartenAufMaterial " + timestamp + ", fertigungFehlgeschlagen = " + fertigungFehlgeschlagen + ", statusZeitstempel_fertigungFehlgeschlagen " + timestamp 
//                + "WHERE ID = " + id + "";
        if(a.isAngenommen() == true)
    	{
        	 a.setStatusZeitstempel_angenommen(timestampNew);
    		 sql = "UPDATE Auftrag SET angeommen = " + true + ", statusZeitstempel_angenommen = " + timestampNew + " WHERE ID = " + a.getId() + "";
    		  try {
    	            db.updateQuery(sql);
    	        } catch (SQLException e) {
    	            e.printStackTrace();
    	        }
    	}
    	else if(a.isAngenommen() == false)
    	{
    		 a.setStatusZeitstempel_angenommen(timestampNew);
    		 sql = "UPDATE Auftrag SET angeommen = " + false + ", statusZeitstempel_angenommen = " + timestampNew + " WHERE ID = " + a.getId() + "";
    		  try {
    	            db.updateQuery(sql);
    	        } catch (SQLException e) {
    	            e.printStackTrace();
    	        }
    	}
    	else if(a.isGefertigt() == true)
    	{
    		a.setStatusZeitstempel_gefertigt(timestampNew);
    		 sql = "UPDATE Auftrag SET gefertigt = " + true + ", statusZeitstempel_gefertigt = " + timestampNew + " WHERE ID = " + a.getId() + "";
    		  try {
    	            db.updateQuery(sql);
    	        } catch (SQLException e) {
    	            e.printStackTrace();
    	        }
    	}
    	else if(a.isGefertigt() == false)
    	{
    		a.setStatusZeitstempel_gefertigt(timestampNew);
    		 sql = "UPDATE Auftrag SET gefertigt = " + false + ", statusZeitstempel_gefertigt = " + timestampNew + " WHERE ID = " + a.getId() + "";
    		  try {
    	            db.updateQuery(sql);
    	        } catch (SQLException e) {
    	            e.printStackTrace();
    	        }
    	}
    	else if(a.isKosten_kalkuliert() == true)
    	{
    		a.setStatusZeitstempel_kosten_kalkuliert(timestampNew);
    		 sql = "UPDATE Auftrag SET kosten_kalkuliert = " + true + ", statusZeitstempel_gefertigt = " + timestampNew + " WHERE ID = " + a.getId() + "";
    		  try {
    	            db.updateQuery(sql);
    	        } catch (SQLException e) {
    	            e.printStackTrace();
    	        }
    	}
    	else if(a.isKosten_kalkuliert() == false)
    	{
    		a.setStatusZeitstempel_kosten_kalkuliert(timestampNew);
    		 sql = "UPDATE Auftrag SET kosten_kalkuliert = " + false + ", statusZeitstempel_gefertigt = " + timestampNew + " WHERE ID = " + a.getId() + "";
    		  try {
    	            db.updateQuery(sql);
    	        } catch (SQLException e) {
    	            e.printStackTrace();
    	        }
    	}
    	else if(a.isAbgeholt() == true)
    	{
    		a.setStatusZeitstempel_abgeholt(timestampNew);
    		 sql = "UPDATE Auftrag SET abgeholt = " + true + ", statusZeitstempel_gefertigt = " + timestampNew + " WHERE ID = " + a.getId() + "";
    		  try {
    	            db.updateQuery(sql);
    	        } catch (SQLException e) {
    	            e.printStackTrace();
    	        }
    	}
    	else if(a.isAbgeholt() == false)
    	{
    		a.setStatusZeitstempel_abgeholt(timestampNew);
    		 sql = "UPDATE Auftrag SET abgeholt = " + false + ", statusZeitstempel_gefertigt = " + timestampNew + " WHERE ID = " + a.getId() + "";
    		  try {
    	            db.updateQuery(sql);
    	        } catch (SQLException e) {
    	            e.printStackTrace();
    	        }
    	}
    	else if(a.isAbgerechnet() == true)
    	{
    		a.setStatusZeitstempel_abgerechnet(timestampNew);
    		 sql = "UPDATE Auftrag SET abgerechnet = " + true + ", statusZeitstempel_gefertigt = " + timestampNew + " WHERE ID = " + a.getId() + "";
    		  try {
    	            db.updateQuery(sql);
    	        } catch (SQLException e) {
    	            e.printStackTrace();
    	        }
    	}
    	else if(a.isAbgerechnet() == false)
    	{
    		a.setStatusZeitstempel_abgerechnet(timestampNew);
    		 sql = "UPDATE Auftrag SET abgerechnet = " + false + ", statusZeitstempel_gefertigt = " + timestampNew + " WHERE ID = " + a.getId() + "";
    		  try {
    	            db.updateQuery(sql);
    	        } catch (SQLException e) {
    	            e.printStackTrace();
    	        }
    	}
    	else if(a.isWartenAufMaterial() == true)
    	{
    		a.setStatusZeitstempel_wartenAufMaterial(timestampNew);
    		 sql = "UPDATE Auftrag SET wartenAufMaterial = " + true + ", statusZeitstempel_gefertigt = " + timestampNew + " WHERE ID = " + a.getId() + "";
    		  try {
    	            db.updateQuery(sql);
    	        } catch (SQLException e) {
    	            e.printStackTrace();
    	        }
    	}
    	else if(a.isWartenAufMaterial() == false)
    	{
    		a.setStatusZeitstempel_wartenAufMaterial(timestampNew);
    		 sql = "UPDATE Auftrag SET wartenAufMaterial = " + false + ", statusZeitstempel_gefertigt = " + timestampNew + " WHERE ID = " + a.getId() + "";
    		  try {
    	            db.updateQuery(sql);
    	        } catch (SQLException e) {
    	            e.printStackTrace();
    	        }
    	}
    	else if(a.isFertigungFehlgeschlagen() == true)
    	{
    		a.setStatusZeitstempel_fertigungFehlgeschlagen(timestampNew);
    		 sql = "UPDATE Auftrag SET fertigungFehlgeschlagen = " + true + ", statusZeitstempel_gefertigt = " + timestampNew + " WHERE ID = " + a.getId() + "";
    		  try {
    	            db.updateQuery(sql);
    	        } catch (SQLException e) {
    	            e.printStackTrace();
    	        }
    	}
    	else if(a.isFertigungFehlgeschlagen() == false)
    	{
    		a.setStatusZeitstempel_fertigungFehlgeschlagen(timestampNew);
    		 sql = "UPDATE Auftrag SET fertigungFehlgeschlagen = " + false + ", statusZeitstempel_gefertigt = " + timestampNew + " WHERE ID = " + a.getId() + "";
    		  try {
    	            db.updateQuery(sql);
    	        } catch (SQLException e) {
    	            e.printStackTrace();
    	        }
    	}
    }
//    
//    int id, boolean angenommen, boolean gefertigt, boolean kosten_kalkuliert,
//    boolean abgeholt, boolean abgerechnet, boolean wartenAufMaterial, boolean fertigungFehlgeschlagen,Timestamp statusZeitstempel_angenommen,
//    Timestamp statusZeitstempel_gefertigt,Timestamp statusZeitstempel_kosten_kalkuliert,Timestamp statusZeitstempel_abgeholt,Timestamp statusZeitstempel_abgerechnet,
//    Timestamp statusZeitstempel_wartenAufMaterial
    
//    public void updateStatusAngenommen(int id, boolean angenommen)
//    {
//    	String sql = "";
//    	Db db = new Db();
//    	Timestamp timestampNew = new Timestamp(System.currentTimeMillis());
//    	if(angenommen == true)
//    	{
//    		 sql = "UPDATE Auftrag SET angeommen = " + true + ", statusZeitstempel_angenommen = " + timestampNew + " WHERE ID = " + id + "";
//    	}
//    	else
//    	{
//    		 sql = "UPDATE Auftrag SET angeommen = " + false + ", statusZeitstempel_angenommen = " + timestampNew + " WHERE ID = " + id + "";
//    	}
//    	try {
//    		db.updateQuery(sql);
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//    	
//    }
//    public void updateStatusGefertigt(int id, boolean gefertigt) 
//    {
//    	String sql = "";
//    	Db db = new Db();
//    	Timestamp timestampNew = new Timestamp(System.currentTimeMillis());
//    	if(gefertigt == true)
//    	{
//    		 sql = "UPDATE Auftrag SET gefertigt = " + true + ", statusZeitstempel_gefertigt = " + timestampNew + " WHERE ID = " + id + "";
//    	}
//    	else
//    	{
//    		 sql = "UPDATE Auftrag SET gefertigt = " + false + ", statusZeitstempel_gefertigt = " + timestampNew + " WHERE ID = " + id + "";
//    	}
//    	try {
//    		db.updateQuery(sql);
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//	}
//    public void updateStatusKosten_kalkuliert(int id, boolean kosten_kalkuliert) 
//    {
//    	String sql = "";
//    	Db db = new Db();
//    	Timestamp timestampNew = new Timestamp(System.currentTimeMillis());
//    	if(kosten_kalkuliert == true)
//    	{
//    		 sql = "UPDATE Auftrag SET kosten_kalkuliert = " + true + ", statusZeitstempel_gefertigt = " + timestampNew + " WHERE ID = " + id + "";
//    	}
//    	else
//    	{
//    		 sql = "UPDATE Auftrag SET kosten_kalkuliert = " + false + ", statusZeitstempel_gefertigt = " + timestampNew + " WHERE ID = " + id + "";
//    	}
//    	try {
//    		db.updateQuery(sql);
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//	}
//    public void updateStatusAbgeholt(int id, boolean abgeholt) 
//    {
//    	String sql = "";
//    	Db db = new Db();
//    	Timestamp timestampNew = new Timestamp(System.currentTimeMillis());
//    	if(abgeholt == true)
//    	{
//    		 sql = "UPDATE Auftrag SET abgeholt = " + true + ", statusZeitstempel_gefertigt = " + timestampNew + " WHERE ID = " + id + "";
//    	}
//    	else
//    	{
//    		 sql = "UPDATE Auftrag SET abgeholt = " + false + ", statusZeitstempel_gefertigt = " + timestampNew + " WHERE ID = " + id + "";
//    	}
//    	try {
//    		db.updateQuery(sql);
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//	}
//    public void updateStatusAbgerechnet(int id, boolean abgerechnet) 
//    {
//    	String sql = "";
//    	Db db = new Db();
//    	Timestamp timestampNew = new Timestamp(System.currentTimeMillis());
//    	if(abgerechnet == true)
//    	{
//    		 sql = "UPDATE Auftrag SET abgerechnet = " + true + ", statusZeitstempel_gefertigt = " + timestampNew + " WHERE ID = " + id + "";
//    	}
//    	else
//    	{
//    		 sql = "UPDATE Auftrag SET abgerechnet = " + false + ", statusZeitstempel_gefertigt = " + timestampNew + " WHERE ID = " + id + "";
//    	}
//    	try {
//    		db.updateQuery(sql);
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//	}
//    public void updateStatusWartenAufMaterial(int id, boolean wartenAufMaterial) 
//    {
//    	String sql = "";
//    	Db db = new Db();
//    	Timestamp timestampNew = new Timestamp(System.currentTimeMillis());
//    	if(wartenAufMaterial == true)
//    	{
//    		 sql = "UPDATE Auftrag SET wartenAufMaterial = " + true + ", statusZeitstempel_gefertigt = " + timestampNew + " WHERE ID = " + id + "";
//    	}
//    	else
//    	{
//    		 sql = "UPDATE Auftrag SET wartenAufMaterial = " + false + ", statusZeitstempel_gefertigt = " + timestampNew + " WHERE ID = " + id + "";
//    	}
//    	try {
//    		db.updateQuery(sql);
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//	}
//    public void updateStatusFertigungFehlgeschlagen(int id, boolean fertigungFehlgeschlagen) 
//    {
//    	String sql = "";
//    	Db db = new Db();
//    	Timestamp timestampNew = new Timestamp(System.currentTimeMillis());
//    	if(fertigungFehlgeschlagen == true)
//    	{
//    		 sql = "UPDATE Auftrag SET fertigungFehlgeschlagen = " + true + ", statusZeitstempel_gefertigt = " + timestampNew + " WHERE ID = " + id + "";
//    	}
//    	else
//    	{
//    		 sql = "UPDATE Auftrag SET fertigungFehlgeschlagen = " + false + ", statusZeitstempel_gefertigt = " + timestampNew + " WHERE ID = " + id + "";
//    	}
//    	try {
//    		db.updateQuery(sql);
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//	}
}
