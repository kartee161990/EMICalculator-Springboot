package com.rabo.emi.integration;

import com.rabo.emi.model.LoanDetails;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.http.HttpHeaders.ACCEPT;
import static org.springframework.http.HttpHeaders.CONTENT_TYPE;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
class EmiCalculatorApplicationTests {

	@Autowired
	private WebTestClient webTestClient;

	@Test
	void shouldCalculateEmiAmount() {
		var loanRequest = createRequest();
		var loanResponse = createResponse();

		var response = webTestClient
				.post()
				.uri("api/loan")
				.body(Mono.just(loanRequest), LoanDetails.class)
				.header(CONTENT_TYPE, APPLICATION_JSON_VALUE)
				.header(ACCEPT, APPLICATION_JSON_VALUE)
				.exchange()
				.expectStatus()
				.isCreated()
				.expectBody(LoanDetails.class)
				.returnResult()
				.getResponseBody();

        assert response != null;
        assertEquals(response.getEmi(), loanResponse.getEmi());

	}

	@Test
	void shouldThrowBadRequestError() {
		var loanRequest = createRequest();
		// test invalid loan type
		loanRequest.setLoanType("Property");

		webTestClient
				.post()
				.uri("api/loan")
				.body(Mono.just(loanRequest), LoanDetails.class)
				.header(CONTENT_TYPE, APPLICATION_JSON_VALUE)
				.header(ACCEPT, APPLICATION_JSON_VALUE)
				.exchange()
				.expectStatus()
				.isBadRequest();


		// test invalid loan value
		// reset original values
		loanRequest =  createRequest();

		loanRequest.setLoanValue(-10.0);

		webTestClient
				.post()
				.uri("api/loan")
				.body(Mono.just(loanRequest), LoanDetails.class)
				.header(CONTENT_TYPE, APPLICATION_JSON_VALUE)
				.header(ACCEPT, APPLICATION_JSON_VALUE)
				.exchange()
				.expectStatus()
				.isBadRequest();

		// test invalid interest rate
		// reset original values
		loanRequest =  createRequest();

		loanRequest.setInterestRate(110.0);

		webTestClient
				.post()
				.uri("api/loan")
				.body(Mono.just(loanRequest), LoanDetails.class)
				.header(CONTENT_TYPE, APPLICATION_JSON_VALUE)
				.header(ACCEPT, APPLICATION_JSON_VALUE)
				.exchange()
				.expectStatus()
				.isBadRequest();

		// test invalid loan term
		// reset original values
		loanRequest =  createRequest();

		loanRequest.setLoanTerm(35);

		webTestClient
				.post()
				.uri("api/loan")
				.body(Mono.just(loanRequest), LoanDetails.class)
				.header(CONTENT_TYPE, APPLICATION_JSON_VALUE)
				.header(ACCEPT, APPLICATION_JSON_VALUE)
				.exchange()
				.expectStatus()
				.isBadRequest();
	}

	private LoanDetails createRequest() {

		return LoanDetails.builder()
				.loanType("Car")
				.loanValue(12990.0)
				.interestRate(11.0)
				.loanTerm(4)
				.email("test@abc.com")
				.build();

	}

	private LoanDetails createResponse() {
		return LoanDetails.builder()
				.loanType("Car")
				.loanValue(12990.0)
				.interestRate(11.0)
				.loanTerm(4)
				.email("test@abc.com")
				.emi(336)
				.build();
	}

}
