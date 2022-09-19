package com.logistics.api.exception;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@JsonInclude(Include.NON_NULL) //inclui apenas as propriedas não nulas
public class Problema {

	private Integer status;
	private LocalDateTime dataHota;
	private String titulo;
	private List<Campo> campos;
	
	public Problema(Integer status, LocalDateTime dataHota, String titulo) {
		this.status = status;
		this.dataHota = dataHota;
		this.titulo = titulo;
	}

	@Getter
	@AllArgsConstructor
	public static class Campo{
		
		private String nome;
		private String mensagem;
	}
}
