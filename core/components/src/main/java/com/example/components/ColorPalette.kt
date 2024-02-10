package com.example.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.util.extension.adaptive
import com.example.util.extension.stateBorder

@Preview(showBackground = true)
@Composable
fun PreviewColorPalette() {
    Box(
        modifier = Modifier.padding(all = 20.dp)
    ) {
        ColorPalette(
            onColorChange = {}
        )
    }
}

const val ROW_COUNT = 6
const val COLUMN_COUNT = 7

@Composable
fun ColorPalette(
    modifier: Modifier = Modifier,
    onColorChange: (Color) -> Unit
) {

    var color by remember {
        mutableStateOf(Color.White)
    }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(10.dp))
            .background(
                color = MaterialTheme.colorScheme.primaryContainer
            )
            .then(modifier)
    ) {
        repeat(ROW_COUNT) { row ->
            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                repeat(COLUMN_COUNT) { col ->
                    val boxColor = countColor(row,col)
                    Box(
                        modifier = Modifier
                            .weight(0.1f)
                            .padding(4.dp)
                            .clip(RoundedCornerShape(5.dp))
                            .background(
                                color = boxColor
                            )
                            .stateBorder(
                                enabled = boxColor == color,
                                color = Color.adaptive(color),
                                shape = RoundedCornerShape(5.dp),
                                width = 2.dp
                            )
                            .clickable {
                                color = boxColor
                                onColorChange.invoke(color)
                            }
                            .padding(16.dp)
                    )
                }
            }
        }
    }
}

private fun countColor(rIndex: Int, cIndex: Int): Color {
    return Color(
        red = red(rIndex,cIndex),
        green = green(rIndex,cIndex),
        blue = blue(rIndex,cIndex),
        alpha = /*0xFF shl rIndex*/ 0xff
    )
}

private fun alpha(): Int {
    return 0xFF
}

/*
*
*private fun red(rIndex: Int,cIndex: Int): Int {
    return simpleDispersion(rIndex,cIndex,2,0)
}

private fun green(rIndex: Int,cIndex: Int): Int {
    return simpleDispersion(rIndex,cIndex,2,2)
}

private fun blue(rIndex: Int,cIndex: Int): Int {
    return simpleDispersion(rIndex,cIndex,2,4) or 1,4
}
*
*
*
*
* private fun red(rIndex: Int,cIndex: Int): Int {
    return beamDispersion(cIndex,-2)
}

private fun green(rIndex: Int,cIndex: Int): Int {
    return simpleDispersion(rIndex,cIndex,1,1)
}

private fun blue(rIndex: Int,cIndex: Int): Int {
    return simpleDispersion(rIndex,cIndex,2,2)
}
*
* */

private fun red(rIndex: Int,cIndex: Int): Int {
    return simpleDispersion(rIndex,cIndex,1,0)
}

private fun green(rIndex: Int,cIndex: Int): Int {
    return simpleDispersion(rIndex,cIndex,1,2)
}

private fun blue(rIndex: Int,cIndex: Int): Int {
    return simpleDispersion(rIndex,cIndex,1,4)
}


private fun simpleDispersion(rIndex: Int,cIndex: Int, bSize: Int, deviation: Int): Int {
    val shifted = 225 shr ((cIndex - deviation) / bSize)
    return if(shifted < 70) columnDiff(rIndex)
    else shifted

}

private fun columnDiff(rIndex: Int): Int {
    return (220 / ROW_COUNT) * rIndex + 20
}




private fun startDispersion(cIndex: Int, deviation: Int = 0, size: Int = 2): Int {
    return 0xFF shr (cIndex / size - deviation)
}

private fun endDispersion(cIndex: Int): Int {
    return (0x01 shl (cIndex + 1)) + 1
}

private fun centralDispersion(cIndex: Int): Int {
    return (0x00 + shiftedDiff(cIndex))
}

private fun cornersDispersion(cIndex: Int): Int {
    return 0xFF - shiftedDiff(cIndex)
}

private fun beamDispersion(cIndex: Int,deviation: Int): Int {
    return 0x00 + beamDiff(cIndex,deviation)
}

private fun beamDiff(cIndex: Int,deviation: Int): Int {

    val diff = diffToCenter(4)
    val deviationWithMod = deviation % 19

    return when(cIndex) {
        in 0 + deviationWithMod until COLUMN_COUNT / 4 + deviationWithMod -> {
            cIndex * diff
        }
        in COLUMN_COUNT / 4 + deviationWithMod until COLUMN_COUNT / 2 + deviationWithMod -> {
            (COLUMN_COUNT / 2 - cIndex) * diff - 1
        }
        in (COLUMN_COUNT / 2) + deviationWithMod until (COLUMN_COUNT - (COLUMN_COUNT / 4)) + deviationWithMod -> {
            cIndex * diff
        }
        in (COLUMN_COUNT - (COLUMN_COUNT / 4)) + deviationWithMod until COLUMN_COUNT + deviationWithMod -> {
            (COLUMN_COUNT - cIndex) * diff - 1

        }
        else -> {
            0
        }
    }
}

private fun shiftedDiff(cIndex: Int): Int {
    return when {
        /*1..7*/
        cIndex < COLUMN_COUNT / 2 -> {
            cIndex * diffToCenter()
        }

        cIndex == COLUMN_COUNT / 2 -> {
            cIndex - 1 * diffToCenter() - 1
        }
        cIndex == COLUMN_COUNT / 2 + 1 -> {
            cIndex - 1 * diffToCenter() - 1
        }
        /*9..7*/
        else -> {
            COLUMN_COUNT - cIndex * diffToCenter()
        }
    }

}

private fun diffToCenter(parts: Int = 2): Int {
    return 256 / COLUMN_COUNT * parts
}
