// [Template no Kotlin Playground](https://pl.kotl.in/WcteahpyN)

import java.time.LocalDate

enum class Nivel { BASICO, INTERMEDIARIO, DIFICIL }

class Usuario(val nome: String) {
    val formacoesMatriculadas: MutableList<Formacao> = mutableListOf()
    val conteudoAssistido: MutableList<ConteudoEducacional> = mutableListOf()

    fun matricular(formacao: Formacao) {
        formacoesMatriculadas.add(formacao)
        formacao.matricular(this)
    }

    fun mostrarFormacoesMatriculadas(): List<Formacao> {
        return formacoesMatriculadas
    }

    fun checkout(conteudo: ConteudoEducacional) {
        conteudoAssistido.add(conteudo)
    }
}

data class ConteudoEducacional(
    val nome: String,
    val duracao: Int = 60,
    val dataLancamento: LocalDate,
    val autor: String,
    val descricao: String,
    val nivel: Nivel
)

class Formacao(val nome: String, val conteudos: MutableList<ConteudoEducacional>) {
    val inscritos: MutableList<Usuario> = mutableListOf()

    fun matricular(usuario: Usuario) {
        inscritos.add(usuario)
    }

    fun adicionarConteudo(conteudo: ConteudoEducacional) {
        conteudos.add(conteudo)
    }

    fun removerConteudo(nomeConteudo: String) {
        conteudos.removeIf { it.nome == nomeConteudo }
    }

    fun buscarConteudo(nomeConteudo: String): ConteudoEducacional? {
        return conteudos.find { it.nome == nomeConteudo }
    }

    fun filtrarPorNivel(nivel: Nivel): List<ConteudoEducacional> {
        return conteudos.filter { it.nivel == nivel }
    }
}

fun main() {
    val formacao1 = Formacao("Desenvolvimento Android com Kotlin", mutableListOf(
        ConteudoEducacional("Introdução ao Android", 60, LocalDate.now(), "John Doe", "Uma breve introdução ao desenvolvimento de aplicativos para a plataforma Android", Nivel.BASICO),
        ConteudoEducacional("Desenvolvimento de layouts", 90, LocalDate.now(), "Jane Smith", "Aprenda a desenvolver layouts eficientes e atrativos para seus aplicativos Android", Nivel.INTERMEDIARIO)
    ))
    
    val usuario1 = Usuario("Joseph")
    val usuario2 = Usuario("Jane")
    formacao1.matricular(usuario1)
    formacao1.matricular(usuario2)
    println("Formação: ${formacao1.nome} - Número de inscritos: ${formacao1.inscritos.size}")

    val conteudo1 = formacao1.conteudos.first()
    usuario1.checkout(conteudo1)
    println("Usuário: ${usuario1.nome} - Conteúdo assistido: ${usuario1.conteudoAssistido}")
}

