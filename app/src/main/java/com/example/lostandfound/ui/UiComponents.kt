package com.example.lostandfound.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
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
import com.example.lostandfound.ui.theme.pinStyle


@Composable
fun AppButtonBlack(text: String, modifier: Modifier, onClick: () -> Unit) {
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
fun AppButton(text: String, modifier: Modifier, onClick: () -> Unit) {
    Button(
        modifier = modifier,
        onClick = onClick, shape = RoundedCornerShape(10.dp), border = BorderStroke(1.dp,Color.Black),
        colors = ButtonDefaults.buttonColors(Color.Transparent)
    ) {

        Text(
            text = text, color = Color.Black, fontSize = 16.sp, fontFamily = Poppins,
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




@Composable
fun PinField() {
    val context= LocalContext.current
    var firstDigitState by remember {
        mutableStateOf("")
    }
    var secondDigitState by remember {
        mutableStateOf("")
    }
    var thirdDigitState by remember {
        mutableStateOf("")
    }
    var fourthDigitState by remember {
        mutableStateOf("")
    }
    var fifthDigitState by remember {
        mutableStateOf("")
    }


    var focusRequest = remember{
        FocusRequester()
    }
    var positionState by remember{
        mutableStateOf(
            0
        )
    }
    var changePosition:(Boolean)->Unit = {
            it->
        if(positionState<4) {
            if(it) positionState += 1
            else focusRequest.requestFocus()
        }


    }



    var invalidatePin:(Int)->Unit ={
            position->
        for(i in position .. 4){
            when(i){
                0->firstDigitState=""
                1->secondDigitState=""
                2->thirdDigitState=""
                3->fourthDigitState=""
                4->fifthDigitState=""


            }

        }
        positionState=position
    }


    LaunchedEffect(key1 = positionState) {
        if(positionState<=4 ) {
            focusRequest.requestFocus()

        }
    }


//    when(verifyEmailState){
//        is UIState.InitialState->{


            Row(modifier = Modifier.padding(top = 47.dp, start = 20.dp, end = 10.dp)) {
                PinTextField(digit = firstDigitState, onChange = {
                    firstDigitState = it
                },focusRequester = if(positionState==0) focusRequest else null,changePosition
                    , invalidatePinChange = invalidatePin,
                    position = 0,currentPosition = positionState)
                Box(modifier = Modifier.padding(end = 10.dp))

                PinTextField(digit = secondDigitState, onChange = {
                    secondDigitState = it
                },changePosition=changePosition,focusRequester = if(positionState==1) focusRequest else null
                    , invalidatePinChange = invalidatePin
                    , position = 1,currentPosition = positionState)
                Box(modifier = Modifier.padding(end = 10.dp))

                PinTextField(digit = thirdDigitState, onChange = {
                    thirdDigitState = it
                },changePosition=changePosition,focusRequester = if(positionState==2) focusRequest else null,
                    invalidatePinChange = invalidatePin
                    , position = 2, currentPosition = positionState)
                Box(modifier = Modifier.padding(end = 10.dp))

                PinTextField(digit = fourthDigitState, onChange = {
                    fourthDigitState = it
                },changePosition=changePosition,focusRequester = if(positionState==3) focusRequest else null
                    , invalidatePinChange = invalidatePin,
                    position=3,currentPosition = positionState)
                Box(modifier = Modifier.padding(end = 10.dp))

                PinTextField(digit = fifthDigitState, onChange = {
                    fifthDigitState = it
                },changePosition=changePosition,focusRequester = if(positionState==4) focusRequest else null,
                    invalidatePinChange = invalidatePin,
                    position=4,currentPosition = positionState)
            }

//            if(positionState<4){
//                Text(
//                    text = "Resend code", style = subText,
//                    modifier = Modifier.padding(top = 42.dp),
//                    textAlign = TextAlign.Center,
//                    textDecoration = TextDecoration.Underline,
//                    color = Color.Black,
//                    fontSize = 14.sp
//                )
//
//            }



//            if(positionState== 4) {
//
//
//                Column(
//                    modifier = Modifier.fillMaxWidth(),
//                    horizontalAlignment = Alignment.CenterHorizontally
//                ) {
//                    AppButton("Verify",Modifier.fillMaxWidth().padding(top = 45.dp, start = 20.dp, end = 20.dp ).height(56.dp)) { }
//
//
//                    Row (modifier = Modifier.fillMaxWidth().align(Alignment.CenterHorizontally).padding(top = 20.dp)){
//                        Text(
//                            text = "Send code again",
//                            style = labelTextStyle,
//                            textAlign = TextAlign.Right,
//                            color = Color.Black
//
//                        )
//
//                        Text(
//                            text = "00:20",
//                            style = labelTextStyle,
//                            textAlign = TextAlign.Right,
//                            color = Color.Black,
//                            fontWeight = FontWeight.Bold,
//                            modifier = Modifier.padding(start = 3.dp)
//
//                        )
//                    }
//
//
//                }
//
//            }


        }






@Composable
fun PinTextField(digit:String,
                 onChange:(String)->Unit
                 , focusRequester: FocusRequester?=null,
                 changePosition:(Boolean)->Unit,
                 invalidatePinChange:(Int)->Unit,
                 position:Int,
                 currentPosition:Int){


    TextField(
        value = digit,
        onValueChange = {

            if (it.length <= 1) onChange(it)
            if(it.length==1){
                changePosition(true)
            }
            else if(it.isEmpty()){
                invalidatePinChange(position)
            }
        },
        singleLine = true,
        modifier = if(focusRequester==null){
            Modifier
                .width(60.dp)
                .border(2.dp, Color(0xFF262626), shape = RoundedCornerShape(10.dp))
                .background(Color.Transparent)
                .padding(start = 5.dp)
                .focusRequester(FocusRequester())
//                    .onFocusChanged {
//                        if (it.isFocused) {
//                            if (currentPosition < position) {
//                                changePosition(false)
//                            }
//                        }
//                    }


        }
        else{
            Modifier
                .width(60.dp)
                .background(Color.Transparent)
                .border(2.dp, Color(0xFF262626), shape = RoundedCornerShape(10.dp))
                .padding(start = 5.dp)
                .focusRequester(focusRequester)
        }

        ,
        colors = TextFieldDefaults.colors(
            focusedContainerColor =  Color.Transparent,
            unfocusedContainerColor =  Color.Transparent,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent
        ),
        textStyle = pinStyle,
        shape = RoundedCornerShape(10.dp),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
    )

}

