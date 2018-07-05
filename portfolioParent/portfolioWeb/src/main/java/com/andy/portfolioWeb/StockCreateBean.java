package com.andy.portfolioWeb;

import java.io.Serializable;
import java.util.List;
import java.util.logging.Logger;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.andy.helper.MessageBean;
import com.andy.session.StockSession;
import com.andy.vd.StockDetailVD;


@SessionScoped
@Named("StockCreateBean")
public class StockCreateBean implements Serializable{
		private static final long serialVersionUID = 2305437966259642981L;
		private static Logger logger = Logger.getLogger("com.andy.bankWeb.StockCreateBean");
		
		
		@Inject 
		private StockSession stockSession;
		@Inject
		private StockDetailVD stockDetailVD;
		@Inject
		private MessageBean messageBean;
		
		
		public StockCreateBean() {}
		
		public String stockCreateAction() {	
			logger.info("Starting stockCreateAction");
			String name = stockDetailVD.getName();
			if(name == null || name.length() == 0) {
				messageBean.setMessage("No stock created" );
				return "ERROR";
			}
			
			boolean success = stockSession.createStock(name);		
			if (success) {
				logger.info("Success sendStockCreateAction");
				return "STOCK_CREATED";
			} else {
				logger.info("Error sendStockCreateAction");
				messageBean.setMessage("Stock already exists" );
				return "ERROR";
			}
		}
		
		public void setName(String name) {
			stockDetailVD.setName(name);
		}
		
		
		public String getName() {
			return stockDetailVD.getName();
		}

	}

