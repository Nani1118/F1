package test;
import java.util.*;
import java.sql.*;
public class DBcon3 
{
	public static void main(String[] args) 
	{
		Scanner s = new Scanner(System.in);
		try(s;)
		{
			try {
				Class.forName("oracle.jdbc.driver.OracleDriver");
				Connection con = DriverManager.getConnection
						("jdbc:Oracle:thin:@localhost:1539:orcl","system","MYLOVE");
				PreparedStatement ps1 = con.prepareStatement
						("insert into product59 values(?,?,?,?)");
				PreparedStatement ps2 = con.prepareStatement
						("select * from product59");
				PreparedStatement ps3 = con.prepareStatement
						("select * from product59 where code=?");
				PreparedStatement ps4 = con.prepareStatement
						("update product59 set price=?,Qty=? where code=?");
				PreparedStatement ps5 = con.prepareStatement
						("delete product59 where code=?");
				while(true)
				{
					System.out.println("****choice*****");
					System.out.println("\t1.add-product"
					+ "\n\t2.view all-products"
					+ "\n\t3.view product by code"
					+ "\n\t4.update product by code(price,Qty)"
					+ "\n\t5.delete product by code"
					+ "\n\t6.exit");
					System.out.println("enter the choice :");
					int choice = Integer.parseInt(s.nextLine());
					switch(choice)
					{
					case 1:
						System.out.println("===enter product-details===");
						System.out.println("enter the prod-code :");
						String pc = s.nextLine();
						System.out.println("enter the prod-name :");
						String pn = s.nextLine();
						System.out.println("enter the prod-price :");
						float pp = Float.parseFloat(s.nextLine());
						System.out.println("enter the prod-Qty :");
						int pq = Integer.parseInt(s.nextLine());
						
						ps1.setString(1, pc);
						ps1.setString(2, pn);
						ps1.setFloat(3, pp);
						ps1.setInt(4, pq);
						int k1 = ps1.executeUpdate();
							if(k1 > 0 )
					 		{
								System.out.println("product added successfully....");
					 		}
						break;
					case 2:
						ResultSet rs1 = ps2.executeQuery();
						System.out.println("====products details====");
						while(rs1.next())
						{
							System.out.println(rs1.getString(1)+"\t"+rs1.getString(2)
										 +"\t"+rs1.getFloat(3)+"\t"+rs1.getInt(4));
						}
						break;
					case 3:
						System.out.println("enter the view product by code: ");
						String pc2 = s.nextLine();
						ps3.setString(1, pc2);
						ResultSet rs3 = ps3.executeQuery();
						System.out.println("=****product details by code****=");
							if(rs3.next())
							{
								System.out.println(rs3.getString(1)+"\t"+rs3.getString(2)+"\t"+rs3.getFloat(3)+"\t"+rs3.getInt(4));
							}
							else
							{
								System.out.println("invalide p-code....");
							}
						break;
					case 4:
							System.out.println("enter the p-code for updating :");
							String pc4 = s.nextLine();
							ps3.setString(1,pc4);
							ResultSet rs4 = ps3.executeQuery();
							if(rs4.next())
							{
								System.out.println("old price of product :"+rs4.getFloat(3));
								System.out.println("enter the new price");
								float np = Float.parseFloat(s.nextLine());
								System.out.println("old Qty of product :"+rs4.getInt(4));
								System.out.println("enter the new Qty :");
								int nq = Integer.parseInt(s.nextLine());
								ps4.setFloat(1,np);
								ps4.setInt(2, nq);
								ps4.setString(3, pc4);
								int k4 = ps4.executeUpdate();
									if(k4>0)
									{
									System.out.println("product details updated successfully....");
									}
									else
									{
									System.out.println("invalide prod-code.......");
									}
							}
							break;
					case 5:
							System.out.println("enter the delete p-code :");
							String dp = s.nextLine();
							ps3.setString(1, dp);
							ResultSet rs5 = ps3.executeQuery();
							if(rs5.next())
							{
								ps5.setString(1,dp);
								int k = ps5.executeUpdate();
									if(k>0)
									{
										System.out.println("product deleted successfully.....");
									}
									else 
									{
										System.out.println("invalide prod-codeee....");
									}
							}
						break;
					case 6:
						System.out.println("Operation Stopped.......");
						System.exit(0);
						break;
					default :
						System.out.println("invalide choice......");
						break;
					}
				}
			}
			catch(Exception e)
			{
				System.out.println(e.getMessage());
			}
		}
	}
}
