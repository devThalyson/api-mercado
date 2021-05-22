package br.com.mercadoApi.controller;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.mercadoApi.model.Cliente;
import br.com.mercadoApi.service.ClienteService;

@RestController
@RequestMapping("api/v1/teste")
public class Teste {

	@Autowired
	ClienteController controller;

	@Autowired
	ClienteService service;

	@GetMapping
	public void testeJogadorETime() throws ParseException {

		//////// Teste de jogador//////////
		// Jogador jogador = new Jogador();
		// SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy");
		// String minhaData = "20/02/2000";
		// Date minhaDataEmDate = sdf1.parse(minhaData);
		// jogador.setNome("Thalyson");
		// jogador.setDataNascimento(minhaDataEmDate);
		// jogadorService.saveJogador(jogador);

		Cliente cliente = new Cliente();

		cliente.setEmail("devthalyson@gmail.com");
		cliente.setNome("Thalyson Moura");
		cliente.setSenha("123456");
		cliente.setTelefone("88981872350");

		service.saveCliente(cliente);

		//////// Teste de time//////////
		// Time time = new Time();
		// time.setNome("Santos");
		// timeService.saveTime(time);

		//////// Teste de rodada //////////
		// Rodada rodada = new Rodada();
		// rodada.setNome("Rodada 2");
		// rodadaService.saveRodada(rodada);

		//////// Teste de partida //////////
		// Partida partida = new Partida();
		// List<Time> times = new ArrayList();
		// times.add(timeService.getTimeById(1L).get());
		// times.add(timeService.getTimeById(2L).get());
		// partida.setTimes(times);
		// partidaService.savePartida(partida);

	}

}
