package ifsuldeminas.academico;

import ifsuldeminas.alunos.Aluno;
import ifsuldeminas.funcionarios.Professor;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Disciplina {
	
	private String nome;
	private int periodo;
	private int numAulasSemana;
	private int numTotalAulas;
	private Professor professor;
	private ArrayList<Aluno> alunos = new ArrayList<Aluno>();
	private ArrayList<Double> notas = new ArrayList<Double>();
	private ArrayList<Integer> frequencias = new ArrayList<Integer>();
	
	public Disciplina(String nome, int periodo, int numAulasSemana,int numSemanas) {
		this.nome = nome;
		this.periodo = periodo;
		this.numAulasSemana = numAulasSemana;
		this.numTotalAulas = numAulasSemana*numSemanas;
	}
	
	public Disciplina(String nome, int periodo, int numAulasSemana,int numSemanas, Professor professor){
		this.nome = nome;
		this.periodo = periodo;
		this.numAulasSemana = numAulasSemana;
		this.numTotalAulas = numAulasSemana*numSemanas;
		this.professor = professor;
	}
	
	public boolean matricularAluno(Aluno aluno) {
		
		for(int i=0;i<alunos.size(); i++) {
			if (alunos.get(i).getRa() == aluno.getRa()) return false;
		}
		
		alunos.add(aluno);
		notas.add(0.0);
		frequencias.add(0);
		
		return true;
	}
	
	public boolean desmatricularAluno(int posAluno) {
		
		if (alunos.size() - 1 < posAluno) return false;
		
		alunos.remove(posAluno);
		notas.remove(posAluno);
		frequencias.remove(posAluno);
		
		return true;
	}
	
	public void removeProfessor() {
		professor = null;
	}
	
	public int getNumeroAlunos() {
		return alunos.size();
	}
	
	public boolean addNota(int posAluno, double nota) {
		
		if (nota < 0 || nota > 10) return false;
		if (alunos.size() - 1 < posAluno) return false;
		
		notas.add(posAluno, nota);
		
		return true;
	}
	
	public boolean addFrequencia(int posAluno, int frequenciaTotal) {
		
		if (frequenciaTotal < 0 || frequenciaTotal > numTotalAulas) return false;
		if (alunos.size() - 1 < posAluno) return false;
		
		frequencias.add(posAluno, frequenciaTotal);
		
		return true;
	}
	
	public boolean estaAprovado(int posAluno) {
		if (notas.get(posAluno) >= 6 && ((float)frequencias.get(posAluno)/numTotalAulas) > 0.75) return true;

		return false;
	}
	
	public void exibirReprovados() {
		
		if (alunos.isEmpty()) System.out.println("Disciplina não possui alunos matriculados");
		
		int countReprovados = 0;
		
		for(int i=0;i<alunos.size(); i++) {
			if (!estaAprovado(i)) {
				alunos.get(i).exibirAluno();
				countReprovados += 1;
			}
		}
		
		if (countReprovados == 0) System.out.println("Não há alunos reprovados.");
	}
	
	public void exibirAprovados() {
		
		if (alunos.isEmpty()) System.out.println("Disciplina não possui alunos matriculados");
		
		int countAprovados = 0;
		
		for(int i=0;i<alunos.size(); i++) {
			if (estaAprovado(i)) {
				alunos.get(i).exibirAluno();
				countAprovados += 1;
			}
		}
		
		if (countAprovados == 0) System.out.println("Não há alunos aprovados.");
	}
	
	public double calculaMedia() {
		
		double sum = 0;
		
		if (alunos.isEmpty()) return sum;
		
		for(int i=0;i<alunos.size(); i++) {
			sum += notas.get(i);
		}
		
		return sum/alunos.size();
	}
	
	public void exibirMaiorNota() {
		
		double maiorNota = 0;
		int indexMaiorNota = 0;
		
		for(int i=0;i<alunos.size(); i++) {
			if(notas.get(i) > maiorNota) 
			{
				maiorNota = notas.get(i);
				indexMaiorNota = i;
			}
		}
		
		alunos.get(indexMaiorNota).exibirAluno();
	}
	
	public void exibirMenorNota() {
		
		double menorNota = Integer.MAX_VALUE;
		int indexMenorNota = 0;
		
		for(int i=0;i<alunos.size(); i++) {
			if(notas.get(i) < menorNota) 
			{
				menorNota = notas.get(i);
				indexMenorNota = i;
			}
		}
		
		alunos.get(indexMenorNota).exibirAluno();
	}
	
	public int getQuantidadeReprovados() {
		
		int countReprovados = 0;
		for(int i=0;i<alunos.size(); i++) {
			if (!estaAprovado(i)) countReprovados += 1;
		}
		return countReprovados;
	}
	
	public int getQuantidadeAprovados() {
		
		int countAprovados = 0;
		for(int i=0;i<alunos.size(); i++) {
			if (estaAprovado(i)) countAprovados += 1;
		}
		return countAprovados;
	}
	
	public void exibirProfessor() {
		if(professor == null) System.out.println("Disciplina não possui professor");
		else professor.exibirProfessor();
	}
	
	public double getAproveitamentoAluno(int posAluno) {
		return (float)frequencias.get(posAluno)/numTotalAulas;
	}
	
	public void exibirAlunosAcimaMedia() {
		double media = calculaMedia();
		
		for(int i = 0; i < alunos.size(); i++) {
			if(notas.get(i) > media) alunos.get(i).exibirAluno(); 
		}
	}
	
	public void desmatricularAlunos(){
		alunos.removeAll(alunos);
		notas.removeAll(notas);
		frequencias.removeAll(frequencias);
	}
	
	public void exibirOrdenadosPorNota(){
		if (alunos.isEmpty()) System.out.println("Disciplina não possui alunos matriculados");
		
		ArrayList<Boolean> ignore = new ArrayList<Boolean>(Collections.nCopies(getQuantidadeAlunos(), false));
		int printed = 0;
		
		while(printed != getQuantidadeAlunos()) {
			double maiorNota = 0;
			int maiorNotaIndex = 0;
			for(int i = 0; i < alunos.size(); i++) {
				if(notas.get(i) > maiorNota && !ignore.get(i)) {
					maiorNota = notas.get(i);
					maiorNotaIndex = i;
				}
			}
			printed += 1;
			ignore.set(maiorNotaIndex, true);
			
			System.out.println(alunos.get(maiorNotaIndex).getNome() + ": " + notas.get(maiorNotaIndex));
		}
	}
	
	public void exibirAlunosAbaixoMedia() {
		double media = calculaMedia();
		
		for(int i = 0; i < alunos.size(); i++) {
			if(notas.get(i) < media) alunos.get(i).exibirAluno(); 
		}
	}		
	
	public String getNome() {
		return nome;
	}
	
	public double getNotaAluno(int posAluno) {
		return notas.get(posAluno);
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getPeriodo() {
		return periodo;
	}

	public void setPeriodo(int periodo) {
		this.periodo = periodo;
	}

	public int getNumAulasSemana() {
		return numAulasSemana;
	}

	public void setNumAulasSemana(int numAulasSemana) {
		this.numAulasSemana = numAulasSemana;
	}

	public int getNumTotalAulas() {
		return numTotalAulas;
	}

	public void setNumTotalAulas(int numTotalAulas) {
		this.numTotalAulas = numTotalAulas;
	}

	public Professor getProfessor() {
		return professor;
	}

	public void setProfessor(Professor professor) {
		this.professor = professor;
	}
	
	public int getQuantidadeAlunos() {
		return alunos.size();
	}
	
	public void exbirAlnosENotasEAproveitamento(){
		for(int i = 0; i < alunos.size(); i++) {
			System.out.println("Aluno: " + alunos.get(i).getNome() + " - nota: " + notas.get(i) + " - frequencia: " + frequencias.get(i));
		}
	}
	
	public void exibirDisciplina() {
		
		System.out.println("Nome: " + nome);
		System.out.println("Periodo: " + periodo);
		System.out.println("Aulas semana: " + numAulasSemana);
		System.out.println("Total de aulas: " + numTotalAulas);
		
		if (alunos.isEmpty()) {
			System.out.println("Disciplina não possui alunos matriculados");
			return;
		}
		
		exbirAlnosENotasEAproveitamento();
		
		System.out.println("Alunos: " + getQuantidadeAlunos());
		System.out.println("Media: " + calculaMedia());
		System.out.println("Aprovados: " + getQuantidadeAprovados());
		System.out.println("Reprovados: " + getQuantidadeReprovados());

	}
	
}
