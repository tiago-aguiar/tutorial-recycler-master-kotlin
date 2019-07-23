package co.tiagoaguiar.recyclermasterkotlin.model

/**
 *
 * Julho, 08 2019
 * @author suporte@moonjava.com.br (Tiago Aguiar).
 */
data class Email(
    val user: String,
    val subject: String,
    val preview: String,
    val date: String,
    val stared: Boolean,
    val unread: Boolean,
    var selected: Boolean = false
)

class EmailBuilder {
    var user: String = ""
    var subject: String = ""
    var preview: String = ""
    var date: String = ""
    var stared: Boolean = false
    var unread: Boolean = false

    fun build(): Email = Email(user, subject, preview, date, stared, unread, false)
}

fun email(block: EmailBuilder.() -> Unit): Email = EmailBuilder().apply(block).build()

fun fakeEmails() = mutableListOf(
    email {
        user = "Facebook"
        subject = "Veja nossas três diasc principais para você conseguir"
        preview = "Olá Tiago, você precisa ver esse site para"
        date = "26 jun"
        stared = false
    },
    email {
        user = "Facebook"
        subject = "Um amigo quer que você curta uma Página dele"
        preview = "Fulano convidou você para curtir a sua pagina"
        date = "30 jun"
        stared = false
    },
    email {
        user = "Youtube"
        subject = "Tiago Aguiar acabou de enviar um video"
        preview = "Tiago Aguiar enviou ANDROID: GOOGLE MAPS, LOCATION"
        date = "26 jun"
        stared = true
        unread = true
    },
    email {
        user = "Instagram"
        subject = "Veja nossas três diasc principais para você conseguir"
        preview = "Olá Tiago, você precisa ver esse site para"
        date = "18 mai"
        stared = false
    },
    email {
        user = "Facebook"
        subject = "Veja nossas três diasc principais para você conseguir"
        preview = "Olá Tiago, você precisa ver esse site para"
        date = "26 jun"
        stared = false
    },
    email {
        user = "Facebook"
        subject = "Um amigo quer que você curta uma Página dele"
        preview = "Fulano convidou você para curtir a sua pagina"
        date = "30 jun"
        stared = false
    },
    email {
        user = "Youtube"
        subject = "Tiago Aguiar acabou de enviar um video"
        preview = "Tiago Aguiar enviou ANDROID: GOOGLE MAPS, LOCATION"
        date = "26 jun"
        stared = true
        unread = true
    },
    email {
        user = "Instagram"
        subject = "Veja nossas três diasc principais para você conseguir"
        preview = "Olá Tiago, você precisa ver esse site para"
        date = "18 mai"
        stared = false
    },
    email {
        user = "Facebook"
        subject = "Veja nossas três diasc principais para você conseguir"
        preview = "Olá Tiago, você precisa ver esse site para"
        date = "26 jun"
        stared = false
    },
    email {
        user = "Facebook"
        subject = "Um amigo quer que você curta uma Página dele"
        preview = "Fulano convidou você para curtir a sua pagina"
        date = "30 jun"
        stared = false
    },
    email {
        user = "Youtube"
        subject = "Tiago Aguiar acabou de enviar um video"
        preview = "Tiago Aguiar enviou ANDROID: GOOGLE MAPS, LOCATION"
        date = "26 jun"
        stared = true
        unread = true
    },
    email {
        user = "Instagram"
        subject = "Veja nossas três diasc principais para você conseguir"
        preview = "Olá Tiago, você precisa ver esse site para"
        date = "18 mai"
        stared = false
    }

)