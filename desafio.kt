enum class Nivel { BASICO, INTERMEDIARIO, DIFICIL }

class Usuario(val nome: String, val email: String, val telefone: Long)

class InformacaoEducacional(val nomeFormacao: String, val tempoDias: Int, val nivel: Nivel)

data class Formacao(val nomeFormacao: String, val nivel: Nivel, val conteudos: MutableList<InformacaoEducacional> = mutableListOf()) {
    private val inscritos = mutableListOf<Usuario>()

    companion object {
        private val inscritosPorFormacaoENivel = mutableMapOf<Pair<String, Nivel>, Int>()

        fun getQuantidadeInscritos(formacao: Formacao): Int {
            val key = Pair(formacao.nomeFormacao, formacao.nivel)
            return inscritosPorFormacaoENivel[key] ?: 0
        }

        fun incrementarQuantidadeInscritos(formacao: Formacao) {
            val key = Pair(formacao.nomeFormacao, formacao.nivel)
            val quantidadeAtual = inscritosPorFormacaoENivel[key] ?: 0
            inscritosPorFormacaoENivel[key] = quantidadeAtual + 1
        }
    }

    fun matricular(usuario: Usuario) {
        inscritos.add(usuario)
        incrementarQuantidadeInscritos(this)
    }

    fun adicionarConteudo(conteudo: InformacaoEducacional) {
        conteudos.add(conteudo)
    }
}

fun main() {
    val matricula1 = Formacao("JavaScript/HTML/CSS", Nivel.INTERMEDIARIO)
    val matricula2 = Formacao("JavaScript/HTML/CSS", Nivel.INTERMEDIARIO)
    val matricula3 = Formacao("WordPress", Nivel.DIFICIL)

    val aluno1 = Usuario("Paula", "pLima@gmail.com", 87992719933)
    val aluno2 = Usuario("João", "joao@gmail.com", 5599271453)
    val aluno3 = Usuario("Lucas", "lucasB@gmail.com", 4599271453)

    val conteudoAluno1 = InformacaoEducacional(matricula1.nomeFormacao, 600, Nivel.BASICO)
    val conteudoAluno2 = InformacaoEducacional(matricula1.nomeFormacao, 40, Nivel.INTERMEDIARIO)

    matricula1.adicionarConteudo(conteudoAluno1)
    matricula2.adicionarConteudo(conteudoAluno2)
    matricula3.adicionarConteudo(conteudoAluno2)
    matricula1.matricular(aluno1)
    matricula2.matricular(aluno2)
    matricula3.matricular(aluno3)

    println("Quantidade de inscritos na formação ${matricula1.nomeFormacao}, nível ${matricula1.nivel}: ${Formacao.getQuantidadeInscritos(matricula1)}")
    println("Quantidade de inscritos na formação ${matricula3.nomeFormacao}, nível ${matricula3.nivel}: ${Formacao.getQuantidadeInscritos(matricula3)}")
}
