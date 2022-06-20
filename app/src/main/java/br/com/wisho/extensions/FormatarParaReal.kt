package br.com.wisho.extensions

import java.math.BigDecimal
import java.text.NumberFormat
import java.util.*

fun BigDecimal.formatarParaReal():String {

    val formatadorMoeda = NumberFormat.getCurrencyInstance(Locale("pt","br"))
     return formatadorMoeda.format(this)

}