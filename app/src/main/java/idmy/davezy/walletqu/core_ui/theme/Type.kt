package idmy.davezy.walletqu.core_ui.theme

import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.text.font.FontWeight.Companion.Normal
import androidx.compose.ui.text.font.FontWeight.Companion.SemiBold
import androidx.compose.ui.unit.sp

// Set of Material typography styles to start with

private val BaseText = TextStyle(
    color = Licorice,
    lineHeight = 24.sp,
    fontFamily = FontFamily.Default,
)

private val TextBold = BaseText.copy(fontWeight = Bold)
private val TextSemiBold = BaseText.copy(fontWeight = SemiBold)
private val TextNormal = BaseText.copy(fontWeight = Normal)

val Title1 = TextBold.copy(fontSize = 24.sp)
val Title2 = TextBold.copy(fontSize = 20.sp)
val Title3 = TextBold.copy(fontSize = 18.sp)
val Title4 = TextBold.copy(fontSize = 16.sp)

val BodySemiBold1 = TextSemiBold.copy(fontSize = 18.sp)
val BodySemiBold2 = TextSemiBold.copy(fontSize = 16.sp)
val BodySemiBold3 = TextSemiBold.copy(fontSize = 14.sp)
val BodySemiBold4 = TextSemiBold.copy(fontSize = 12.sp)

val Body1 = TextNormal.copy(fontSize = 18.sp)
val Body2 = TextNormal.copy(fontSize = 16.sp)
val Body3 = TextNormal.copy(fontSize = 14.sp)
val Body4 = TextNormal.copy(fontSize = 12.sp)