package br.com.caelum.financas.service;

import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;
import javax.ejb.AccessTimeout;
import javax.ejb.Schedule;
import javax.ejb.ScheduleExpression;
import javax.ejb.Singleton;
import javax.ejb.Timeout;
import javax.ejb.Timer;
import javax.ejb.TimerConfig;
import javax.ejb.TimerService;

//@Stateless
@Singleton
@AccessTimeout(unit = TimeUnit.SECONDS, value = 5)
public class Agendador {

	@Resource
	private TimerService timerService;
	
	private static int totalCriado;

	@PostConstruct
	void posConstrucao() {
		System.out.println("criando agendador");
		totalCriado++;
	}
	
	@PreDestroy
	void preDestruicao() {
		System.out.println("destruindo agendador");
	}

	public void agenda(String expressaoMinutos, String expressaoSegundos) {
		
		ScheduleExpression expression = new ScheduleExpression();
		expression.hour("*");
		expression.minute(expressaoMinutos);
		expression.second(expressaoSegundos);
		
		TimerConfig config = new TimerConfig();
		config.setInfo(expression.toString());
		config.setPersistent(false);

		this.timerService.createCalendarTimer(expression, config);
		
		System.out.println("Agendamento: " + expression);
	}
	
	@Timeout
	public void verificacaoDeNovasContas(Timer timer) {
		System.out.println(timer.getInfo());
		System.out.println("Timer expirado");
	}
	
	@Schedule(hour="*",minute="*/1", second="0", persistent=false)
	public void enviaEmailACadaMinuto() {
		System.out.println("enviando e-mail a cada minuto");
	}
	
	public void executa() {
		System.out.printf("%d instancias criadas %n", totalCriado);

		// simulando demora de 4s na execucao
		try {
			System.out.printf("Executando %s %n", this);
			Thread.sleep(4000);
		} catch (InterruptedException e) {
		}
	}

}
