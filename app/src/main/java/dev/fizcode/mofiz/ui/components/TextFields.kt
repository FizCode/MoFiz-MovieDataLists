package dev.fizcode.mofiz.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun SearchInput() {
    var textState by remember { mutableStateOf("") }
    var focusState by remember { mutableStateOf(false) }

    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
            .clip(shape = RoundedCornerShape(100.dp))
            .onFocusChanged { focus ->
                focusState = focus.isFocused
            },
        colors = TextFieldDefaults.colors(
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent

        ),
        value = textState,
        onValueChange = { textState = it },
        label = {
            if (!focusState) {
                Text(
                    modifier = Modifier.padding(horizontal = 16.dp),
                    style = MaterialTheme.typography.bodyLarge,
                    text = "Search"
                )
            }
        },
        trailingIcon = {
            Icon(
                modifier = Modifier.padding(end = 24.dp),
                imageVector = Icons.Rounded.Search,
                contentDescription = "Settings Icon",
                tint = MaterialTheme.colorScheme.primary
            )
        },
        singleLine = true,
        shape = RoundedCornerShape(100.dp)
    )
}

@Composable
fun CustomSearchInput() {
    var text by rememberSaveable { mutableStateOf("") }
    BasicTextField(modifier = Modifier
        .fillMaxWidth()
        .background(
            MaterialTheme.colorScheme.primaryContainer,
            MaterialTheme.shapes.extraLarge,
        )
        .padding(horizontal = 16.dp, vertical = 12.dp),
        value = text,
        onValueChange = {
            text = it
        },
        singleLine = true,
        cursorBrush = SolidColor(MaterialTheme.colorScheme.primary),
        decorationBox = { innerTextField ->
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Icon(
                    Icons.Filled.Search,
                    null,
                    tint = LocalContentColor.current.copy(alpha = 0.4f)
                )
                Box(Modifier.weight(1f)) {
                    if (text.isEmpty()) Text(
                        text = "Search",
                        style = LocalTextStyle.current.copy(
                            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.4f),
                            fontSize = MaterialTheme.typography.bodyLarge.fontSize
                        )
                    )
                    innerTextField()
                }
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun TextFieldsPreview() {
    CustomSearchInput()
}