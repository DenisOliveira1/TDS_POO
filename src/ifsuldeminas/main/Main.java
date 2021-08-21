package ifsuldeminas.main;

import java.util.Scanner;

import ifsuldeminas.academico.Disciplina;
import ifsuldeminas.alunos.Aluno;
import ifsuldeminas.funcionarios.Professor;

public class Main {

	public static void main(String [] args){ 
		
		Aluno a0 = new Aluno("joao 0",1000,1,"computacao");
		Aluno a1 = new Aluno("joao 1",1001,1,"computacao");
		Aluno a2 = new Aluno("joao 2",1002,1,"computacao");
		Aluno a3 = new Aluno("joao 3",1003,1,"computacao");
		Aluno a4 = new Aluno("joao 4",1004,1,"computacao");
		Aluno a5 = new Aluno("joao 5",1005,1,"computacao");
		Aluno a6 = new Aluno("joao 6",1006,1,"computacao");
		Aluno a7 = new Aluno("joao 7",1007,1,"computacao");
		Aluno a8 = new Aluno("joao 8",1008,1,"computacao");
		Aluno a9 = new Aluno("joao 9",1009,1,"computacao");
		
		Professor p1 = new Professor("julio",5001);
		
		Disciplina d1 = new Disciplina("matemática",1,5,2);
		
		d1.matricularAluno(a0);
		d1.matricularAluno(a1);
		d1.matricularAluno(a2);
		d1.matricularAluno(a3);
		d1.matricularAluno(a4);
		d1.matricularAluno(a5);
		d1.matricularAluno(a6);
		d1.matricularAluno(a7);
		d1.matricularAluno(a8);
		d1.matricularAluno(a9);
		
		d1.setProfessor(p1);
		
		d1.addNota(0, 0);
		d1.addNota(1, 1);
		d1.addNota(2, 2);
		d1.addNota(3, 3);
		d1.addNota(4, 4);
		d1.addNota(5, 5);
		d1.addNota(6, 6);
		d1.addNota(7, 7);
		d1.addNota(8, 8);
		d1.addNota(9, 9);
		
		d1.addFrequencia(0, 0);
		d1.addFrequencia(1, 1);
		d1.addFrequencia(2, 2);
		d1.addFrequencia(3, 3);
		d1.addFrequencia(4, 4);
		d1.addFrequencia(5, 5);
		d1.addFrequencia(6, 6);
		d1.addFrequencia(7, 7);
		d1.addFrequencia(8, 8);
		d1.addFrequencia(9, 9);
		
		Scanner s = new Scanner(System.in);
		Integer opcao = 1;
		
		while (opcao != 0){
			System.out.println("Menu");
			System.out.println("1 - Imprimir informações da disciplina");
			System.out.println("2 - Apresentar número de alunos na disciplina, número de reprovados e número de aprovados");
			System.out.println("3 - Imprimir aprovados");
			System.out.println("4 - Imprimir reprovados");
			System.out.println("5 - Imprimir aluno com a maior e o aluno com menor nota");
			System.out.println("6 - Imprimir o professor responsável");
			System.out.println("7 - Imprimir média total da turma e listar todos os alunos acima da média");
			System.out.println("8 - Imprimir média total da turma e listar todos os alunos abaixo da média");
			System.out.println("9 - Imprimir nome dos alunos e suas respectivas notas e aproveitamento");
			System.out.println("10 - Imprimir alunos ordenados por nota");
			System.out.println("11 - Matricular aluno");
			System.out.println("-------------------------------------------");
			opcao = s.nextInt();
			
			if(opcao == 1) {
				d1.exibirDisciplina();
			}
			else if(opcao == 2) {
				System.out.println("Quantidade matriculados: " +d1.getQuantidadeAlunos());
				System.out.println("Quantidade aprovados: " + d1.getQuantidadeAprovados());
				System.out.println("Quantidade reprovados: " + d1.getQuantidadeReprovados());
			}
			else if(opcao == 3) {
				d1.exibirAprovados();
			}
			else if(opcao == 4) {
				d1.exibirReprovados();
			}
			else if(opcao == 5) {
				System.out.print("Maior nota: ");
				d1.exibirMaiorNota();
				System.out.print("Menor nota: ");
				d1.exibirMenorNota();		
			}
			else if(opcao == 6) {
				d1.exibirProfessor();		
			}
			else if(opcao == 7) {
				System.out.println("Média turma: " + d1.calculaMedia());
				d1.exibirAlunosAcimaMedia();	
			}
			else if(opcao == 8) {
				System.out.println("Média turma: " + d1.calculaMedia());
				d1.exibirAlunosAbaixoMedia();	
			}
			else if(opcao == 9) {
				d1.exbirAlnosENotasEAproveitamento();
			}
			else if(opcao == 10) {
				d1.exibirOrdenadosPorNota();
			}
			else if(opcao == 11) {
				
				boolean status = false;
				
				System.out.print("Digite o nome: ");
				String nome = s.nextLine();
				
				int ra = -1;
				while(ra < 0 || ra > 999){
					System.out.print("Digite o ra: ");
					ra = s.nextInt();				
				}
				
				System.out.print("Digite o curso: ");
				String curso = s.next();
				
				double nota = -1;
				while(nota < 0 || nota > 10){
					System.out.print("Digite a nota: ");
					nota = s.nextDouble();				
				}
				
				int frequencia = -1;
				while(frequencia < 0 || frequencia > d1.getNumTotalAulas()){
					System.out.print("Digite a frequencia: ");
					frequencia = s.nextInt();				
				}
				
				Aluno novoAluno = new Aluno(nome, ra, 1, curso);
				status = d1.matricularAluno(novoAluno);
				if (status) {
					d1.addNota(d1.getNumeroAlunos()-1, nota);
					d1.addFrequencia(d1.getNumeroAlunos()-1, frequencia);
					System.out.print("Matriculado com sucesso.\n");
				}
				else System.out.print("Aluno com esse ra já foi matriculado.");

			}
			else if(opcao == 0) {
				s.close();
				d1.desmatricularAlunos();
				d1.removeProfessor();
				break;
			}else{
				System.out.println("Opção inválida");
			}
			System.out.println("-------------------------------------------");
		}
	}
}
