package com.example.lostandfound.ui

import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lostandfound.R
import com.example.lostandfound.ui.theme.Poppins
import com.example.lostandfound.ui.theme.labelTextStyle


@Composable
fun AppButton(text: String, modifier: Modifier, onClick: () -> Unit) {
    Button(
        modifier = modifier,
        onClick = onClick, shape = RoundedCornerShape(10.dp),
        colors = ButtonDefaults.buttonColors(Color.Black)
    ) {

        Text(
            text = text, color = Color.White, fontSize = 16.sp, fontFamily = Poppins,
            fontWeight = FontWeight.SemiBold
        )
    }


}


@Composable
fun PasswordOutlineTextField(modifier: Modifier,text: String,label:String,onTextChange: (String) ->Unit){

    var isPasswordVisible by rememberSaveable {
        mutableStateOf(false)
    }


    OutlinedTextField(
        modifier = modifier,
        label = {
            Text(text = label, style = labelTextStyle)
        },
        onValueChange = {
            onTextChange(it)
        },
        value = text,
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = Color.Black,
            unfocusedBorderColor = Color(0x14000000),
            focusedTextColor = Color.Black,
            unfocusedTextColor = Color.Black
        ),
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Password
        ),
        visualTransformation = if (isPasswordVisible)  (VisualTransformation.None) else PasswordVisualTransformation()
        , trailingIcon = {
            var image = painterResource(R.drawable.password_show)
            if (isPasswordVisible) {
                image = painterResource(R.drawable.password_hide)
            }

            IconButton(onClick = {
                isPasswordVisible = !isPasswordVisible
            })
            {
                Icon(painter = image, contentDescription = "password", tint = Color.Black)
            }
        }
    )
}

