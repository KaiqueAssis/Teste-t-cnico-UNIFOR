package org.manager.students.domain.dto;

import java.util.List;

public record CursoDto(String nome, String uuid, List<AlunoDto> alunoDtos) {
}
