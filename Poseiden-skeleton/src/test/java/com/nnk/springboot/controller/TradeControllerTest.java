package com.nnk.springboot.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

import java.sql.Timestamp;
import java.time.Instant;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import com.nnk.springboot.controllers.TradeController;
import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.repositories.TradeRepository;
import com.nnk.springboot.service.TradeService;

@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ActiveProfiles("test")
public class TradeControllerTest {


	@Autowired
	private TradeController tradeController;


	@Autowired
	private TradeRepository tradeRepository;

	private Trade trade = new Trade();


	@BeforeAll
	void createTrade() {

		trade.setAccount("a");
		trade.setType("a");
		trade.setBuyQuantity(6.0);


	}

	@AfterAll
	void cleanUp() {
		tradeRepository.deleteAll();
	}	

	@Test
	@Order(1)
	public void testHome() {

		Model model = mock(Model.class);

		String result = tradeController.home(model);
		assertEquals("trade/list", result );

	}
	@Test
	@Order(2)
	public void testaddBidForm() {

		String result = tradeController.addUser(trade);

		assertEquals("trade/add", result );

	}
	@Test
	@Order(3)
	public void testValidate() {


		BindingResult bindingResult = mock(BindingResult.class);
		Model model = mock(Model.class);

		String result = tradeController.validate(trade, bindingResult, model);

		assertEquals("redirect:/trade/list", result );

	}

	@Test
	@Order(4)
	public void testShowUpdateForm() {

		Model model = mock(Model.class);
		System.out.println("trade show : " + trade.getTradeId() );
		String result = tradeController.showUpdateForm(trade.getTradeId() , model);

		assertEquals("trade/update", result );

	}

	@Test
	@Order(5)
	public void testUpdateTrade() {
		Trade newTrade = new Trade();

		newTrade.setAccount("b");
		newTrade.setType("b");
		newTrade.setBuyQuantity(5.0);



		Model model = mock(Model.class);
		BindingResult bindingResult = mock(BindingResult.class);
		
		System.out.println("trade update : " + trade.getTradeId() );
		
		String result = tradeController.updateTrade(trade.getTradeId(), newTrade, bindingResult, model);

		assertEquals("redirect:/trade/list", result );

	}

	@Test
	@Order(6)
	public void testDeleteBid() {
		Model model = mock(Model.class);
		String result = tradeController.deleteTrade(trade.getTradeId(), model);
		assertEquals("redirect:/trade/list", result );

	}

}
