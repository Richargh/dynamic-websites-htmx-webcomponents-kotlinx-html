package de.richargh.sandbox.htmx.kotlinxhtml.commons.money.domain


@JvmRecord
data class Euro(val rawCents: Int) {
    override fun toString(): String {
        return (rawCents / 100).toString() + "." + rawCents % 100 + " €"
    }

    companion object {
        fun ofEuros(euros: Int): Euro {
            return Euro(euros * 100)
        }

        fun ofCents(cents: Int): Euro {
            return Euro(cents)
        }
    }
}
