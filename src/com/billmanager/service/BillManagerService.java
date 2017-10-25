package com.billmanager.service;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.billmanager.beans.Bill;
import com.billmanager.dao.BillManagerDao;

@Path("/BillManagerService")
public class BillManagerService {
	
	BillManagerDao bmDao = new BillManagerDao(); 
	
	@GET
	@Path("/bills")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public String getAllBills() {
		return bmDao.getAllBill();
	}
	
	@GET
	@Path("/bills/{billid}")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public String getBills(@PathParam("billid") int billid) {
		return bmDao.getBill(billid);
	}

	@POST
	@Path("/bills")
	@Consumes({ MediaType.APPLICATION_JSON })
	//@Produces({ MediaType.APPLICATION_JSON })
	public int createBills(Bill bill) {
		return bmDao.createBill(bill);
	}

	@PUT
	@Path("/bills/{billid}")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public int updateBills(@PathParam("billid") int billid, Bill bill) {
		return bmDao.updateBill(billid, bill);
	}
	
	@DELETE
	@Path("/bills/{billid}")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public void deleteBills(@PathParam("billid") int billid) {
		bmDao.deleteBill(billid);
	}
}