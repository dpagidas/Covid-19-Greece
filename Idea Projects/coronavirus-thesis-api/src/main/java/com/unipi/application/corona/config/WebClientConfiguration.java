package com.unipi.application.corona.config;

import io.netty.channel.ChannelOption;
import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslContextBuilder;
import io.netty.handler.ssl.util.InsecureTrustManagerFactory;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.reactive.ClientHttpConnector;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.netty.http.client.HttpClient;

import javax.net.ssl.SSLException;


@Configuration
public class WebClientConfiguration {
    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Value("${rtaImanageUrl:https://cy-devwrk001.cy.ema.ad.pwcinternal.com}")
    private String url;

    @Bean
    public WebClient buildVaccinationApiRequest() throws SSLException {
        SslContext sslContext = SslContextBuilder.forClient().trustManager(InsecureTrustManagerFactory.INSTANCE).build();
        HttpClient httpClient = HttpClient.create()
                .secure(t-> t.sslContext(sslContext))
                .tcpConfiguration(client ->
                        client.option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 20000)
                                .doOnConnected(conn -> conn
                                        .addHandlerLast(new ReadTimeoutHandler(60))
                                        .addHandlerLast(new WriteTimeoutHandler(10))));

        ClientHttpConnector connector = new ReactorClientHttpConnector(httpClient);

        WebClient webClient = WebClient.builder()
                .exchangeStrategies(ExchangeStrategies.builder()
                        .codecs(configurer -> configurer
                                .defaultCodecs()
                                .maxInMemorySize(16 * 1024 * 1024))
                        .build())
                .baseUrl("https://covid-19-greece.herokuapp.com")
                .filter(logRequest())
                .clientConnector(connector)
                .build();

        return webClient;
    }

    @Bean
    public WebClient buildCovid19ApiRequest() throws SSLException {
        SslContext sslContext = SslContextBuilder.forClient().trustManager(InsecureTrustManagerFactory.INSTANCE).build();
        HttpClient httpClient = HttpClient.create()
                .secure(t-> t.sslContext(sslContext))
                .tcpConfiguration(client ->
                        client.option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 20000)
                                .doOnConnected(conn -> conn
                                        .addHandlerLast(new ReadTimeoutHandler(60))
                                        .addHandlerLast(new WriteTimeoutHandler(10))));

        ClientHttpConnector connector = new ReactorClientHttpConnector(httpClient);

        WebClient webClient = WebClient.builder()
                .exchangeStrategies(ExchangeStrategies.builder()
                        .codecs(configurer -> configurer
                                .defaultCodecs()
                                .maxInMemorySize(16 * 1024 * 1024))
                        .build())
                .baseUrl("https://newsapi.org")
                .filter(logRequest())
                .clientConnector(connector)
                .build();

        return webClient;
    }

    @Bean
    public ExchangeFilterFunction logRequest() {
        return ExchangeFilterFunction.ofRequestProcessor(clientRequest -> {
            logger.info("Request: {} {}", clientRequest.method(), clientRequest.url());
            clientRequest.headers().forEach((name, values) -> values.forEach(value -> logger.info("{}={}", name, value)));
            return Mono.just(clientRequest);
        });
    }
}
