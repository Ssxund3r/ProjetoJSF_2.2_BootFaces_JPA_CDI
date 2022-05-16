package br.com.threadfilageradormassas;

import java.util.Iterator;
import java.util.concurrent.ConcurrentLinkedQueue;

import br.com.entidades.Pessoa;

public class GeradorMassaFilaThreadImpl extends Thread {

	private static ConcurrentLinkedQueue<Pessoa> pilhaFilaPessoas = new ConcurrentLinkedQueue<Pessoa>();

	public static void add(Pessoa pessoa) {
		pilhaFilaPessoas.add(pessoa);
	}

	@SuppressWarnings("rawtypes")
	@Override
	public void run() {

		while (true) {
			synchronized (pilhaFilaPessoas) { // Bloquear o acesso a esta lista por outro processo

				Iterator iterator = pilhaFilaPessoas.iterator();

				while (iterator.hasNext()) { // Enquanto conter dados irá processar

					Pessoa processo = (Pessoa) iterator.next();

					System.out.println("-----------------");
					System.out.println(processo.getNome());
					System.out.println(processo.getSobrenome());
					System.out.println(processo.getIdade());
					System.out.println(processo.getLogin());
					System.out.println(processo.getSenha());
					System.out.println(processo.getPerfilUser());
					

					try {
						Thread.sleep(500);
					} catch (InterruptedException e) { // Dar um tempo para descarga de memoria
						e.printStackTrace();
					}

				}

			}
			try {
				Thread.sleep(1000); //Processou toda a lista dar uma pausa para limpeza de memória
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}

}
