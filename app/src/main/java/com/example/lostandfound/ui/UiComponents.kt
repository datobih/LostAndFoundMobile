package com.example.lostandfound.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Card
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedCard
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lostandfound.R
import com.example.lostandfound.ui.theme.Poppins
import com.example.lostandfound.ui.theme.labelTextStyle
import com.example.lostandfound.ui.theme.pinStyle
import com.example.lostandfound.ui.theme.text12N
import com.example.lostandfound.ui.theme.text14Medium
import com.example.lostandfound.ui.theme.text14SB


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
        onClick = onClick,
        shape = RoundedCornerShape(10.dp),
        border = BorderStroke(1.dp, Color.Black),
        colors = ButtonDefaults.buttonColors(Color.Transparent)
    ) {

        Text(
            text = text, color = Color.Black, fontSize = 16.sp, fontFamily = Poppins,
            fontWeight = FontWeight.SemiBold
        )
    }


}

@Composable
fun PasswordOutlineTextField(
    modifier: Modifier,
    text: String,
    label: String,
    onTextChange: (String) -> Unit,
    isError:Boolean,
    errorMessage:String
) {

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
        isError =(isError),
        visualTransformation = if (isPasswordVisible) (VisualTransformation.None) else PasswordVisualTransformation(),
        trailingIcon = {
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
    , supportingText = {
        if(isError){
            Text(errorMessage)
        }
        })
}


@Composable
fun PinField() {
    val context = LocalContext.current
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


    var focusRequest = remember {
        FocusRequester()
    }
    var positionState by remember {
        mutableStateOf(
            0
        )
    }
    var changePosition: (Boolean) -> Unit = { it ->
        if (positionState < 4) {
            if (it) positionState += 1
            else focusRequest.requestFocus()
        }


    }


    var invalidatePin: (Int) -> Unit = { position ->
        for (i in position..4) {
            when (i) {
                0 -> firstDigitState = ""
                1 -> secondDigitState = ""
                2 -> thirdDigitState = ""
                3 -> fourthDigitState = ""
                4 -> fifthDigitState = ""


            }

        }
        positionState = position
    }


    LaunchedEffect(key1 = positionState) {
        if (positionState <= 4) {
            focusRequest.requestFocus()

        }
    }


//    when(verifyEmailState){
//        is UIState.InitialState->{


    Row(modifier = Modifier.padding(top = 47.dp, start = 20.dp, end = 10.dp)) {
        PinTextField(
            digit = firstDigitState,
            onChange = {
                firstDigitState = it
            },
            focusRequester = if (positionState == 0) focusRequest else null,
            changePosition,
            invalidatePinChange = invalidatePin,
            position = 0,
            currentPosition = positionState
        )
        Box(modifier = Modifier.padding(end = 10.dp))

        PinTextField(
            digit = secondDigitState,
            onChange = {
                secondDigitState = it
            },
            changePosition = changePosition,
            focusRequester = if (positionState == 1) focusRequest else null,
            invalidatePinChange = invalidatePin,
            position = 1,
            currentPosition = positionState
        )
        Box(modifier = Modifier.padding(end = 10.dp))

        PinTextField(
            digit = thirdDigitState,
            onChange = {
                thirdDigitState = it
            },
            changePosition = changePosition,
            focusRequester = if (positionState == 2) focusRequest else null,
            invalidatePinChange = invalidatePin,
            position = 2,
            currentPosition = positionState
        )
        Box(modifier = Modifier.padding(end = 10.dp))

        PinTextField(
            digit = fourthDigitState,
            onChange = {
                fourthDigitState = it
            },
            changePosition = changePosition,
            focusRequester = if (positionState == 3) focusRequest else null,
            invalidatePinChange = invalidatePin,
            position = 3,
            currentPosition = positionState
        )
        Box(modifier = Modifier.padding(end = 10.dp))

        PinTextField(
            digit = fifthDigitState,
            onChange = {
                fifthDigitState = it
            },
            changePosition = changePosition,
            focusRequester = if (positionState == 4) focusRequest else null,
            invalidatePinChange = invalidatePin,
            position = 4,
            currentPosition = positionState
        )
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
fun PinTextField(
    digit: String,
    onChange: (String) -> Unit, focusRequester: FocusRequester? = null,
    changePosition: (Boolean) -> Unit,
    invalidatePinChange: (Int) -> Unit,
    position: Int,
    currentPosition: Int
) {


    TextField(
        value = digit,
        onValueChange = {

            if (it.length <= 1) onChange(it)
            if (it.length == 1) {
                changePosition(true)
            } else if (it.isEmpty()) {
                invalidatePinChange(position)
            }
        },
        singleLine = true,
        modifier = if (focusRequester == null) {
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


        } else {
            Modifier
                .width(60.dp)
                .background(Color.Transparent)
                .border(2.dp, Color(0xFF262626), shape = RoundedCornerShape(10.dp))
                .padding(start = 5.dp)
                .focusRequester(focusRequester)
        },
        colors = TextFieldDefaults.colors(
            focusedContainerColor = Color.Transparent,
            unfocusedContainerColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent
        ),
        textStyle = pinStyle,
        shape = RoundedCornerShape(10.dp),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
    )

}


@Composable
fun MySearchTextField(
    text: String,
    textFieldChange: (String) -> Unit,
    modifier: Modifier = Modifier
        .padding(start = 20.dp, top = 16.dp)

        .fillMaxWidth(.9f),
    onSearch: (String) -> Unit
) {
    val focusManager = LocalFocusManager.current
    val focusRequester = remember {
        FocusRequester()
    }
    var shouldShowHint by remember {
        mutableStateOf(true)
    }

    val context = LocalContext.current
    Card(
        modifier = modifier, elevation = 4.dp, shape = RoundedCornerShape(10.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(vertical = 10.dp)
                .padding(start = 10.dp)
        ) {
            androidx.compose.material.Icon(painter = painterResource(id = R.drawable.ic_search),
                contentDescription = "Search icon",
                modifier = Modifier
                    .size(15.dp)
                    .clickable { })

            BasicTextField(value = text,
                maxLines = 2,
                textStyle = labelTextStyle,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp)
                    .focusRequester(focusRequester)
                    .onFocusChanged {
                        if (it.isFocused) {
                            shouldShowHint = false
                        } else {
                            if (text.isEmpty()) {
                                shouldShowHint = true

                            }
                        }

                    },
                keyboardOptions = KeyboardOptions(
                    KeyboardCapitalization.None, true, KeyboardType.Text, ImeAction.Search
                ),

                keyboardActions = KeyboardActions(onSearch = {
                    focusManager.clearFocus(true)
                    if (text.isNotEmpty()) onSearch(text)
                }),
                onValueChange = {


                    textFieldChange(it)


                },
                decorationBox = {
                    if (text.isEmpty() && shouldShowHint) {
                        Box(modifier = Modifier.padding(start = 10.dp))
                        androidx.compose.material.Text(text = "Search")
                    } else {
                        it()
                    }
                }

            )
        }

    }

}

@Composable
fun FilterItem(text: String, isSelected: Boolean = false, onClick: () -> Unit) {
    var bg by remember {
        mutableStateOf(Color(0xC8BBBBBB))
    }
    if (isSelected) bg = Color.Black else bg = Color(0xC8BBBBBB)
    Box(
        modifier = Modifier
            .padding(horizontal = 2.dp)
            .clip(RoundedCornerShape(7.dp))
            .background(bg)
            .clickable { onClick() },
    ) {
        Text(
            text = text,
            modifier = Modifier
                .align(Alignment.Center)
                .padding(10.dp, 6.dp),
            style = labelTextStyle,
            color = if (isSelected) Color.White else Color.Black
        )
    }
}


@Composable
fun OutlinedFilterItem(text: String) {
    Box(
        modifier = Modifier
            .padding(horizontal = 2.dp)
            .width(90.dp)
            .border((1.5).dp, Color.Black, RoundedCornerShape(7.dp))
            .background(Color.Transparent),
    ) {
        Text(
            text = text,
            modifier = Modifier
                .align(Alignment.Center)
                .padding(10.dp, 6.dp),
            style = text14Medium,
            color = Color.Black
        )
    }
}


@Composable
fun ItemCard(text: String, description: String) {
    ElevatedCard(
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        modifier = Modifier.padding(5.dp)
    ) {
        Column(
            Modifier
                .fillMaxSize()
                .padding(bottom = 6.dp)
        ) {

            Image(
                painter = painterResource(R.drawable.face),
                contentScale = ContentScale.Fit,
                contentDescription = "item image"
            )

            Text(
                text,
                style = text14SB,
                modifier = Modifier
                    .fillMaxWidth(.8f)
                    .padding(top = 8.dp, start = 10.dp, end = 6.dp),
                color = Color.Black,
                overflow = TextOverflow.Ellipsis
            )


            Row(
                Modifier.padding(top = 4.dp, start = 6.dp, end = 6.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    modifier = Modifier.size(20.dp),
                    painter = painterResource(R.drawable.ic_location),
                    contentScale = ContentScale.Fit,
                    contentDescription = "item image"
                )
                Text(
                    "Laboratory", style = text12N,
                    modifier = Modifier.padding(start = 4.dp), overflow = TextOverflow.Ellipsis
                )
            }

            Row(
                Modifier.padding(top = 4.dp, start = 6.dp, end = 6.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    modifier = Modifier.size(20.dp),
                    painter = painterResource(R.drawable.ic_calendar),
                    contentScale = ContentScale.Fit,
                    contentDescription = "item image"
                )
                Text(
                    "13nd November 2024", style = text12N,
                    modifier = Modifier.padding(start = 4.dp), overflow = TextOverflow.Ellipsis
                )
            }

        }
    }

}

@Composable
fun AdItem() {
    OutlinedCard(
        elevation = CardDefaults.cardElevation(6.dp),
        modifier = Modifier.padding(horizontal = 4.dp, vertical = 8.dp),
        colors = CardDefaults.cardColors(Color.White)
    ) {

        Row(modifier = Modifier.fillMaxWidth()) {

            Image(
                painter = painterResource(R.drawable.face),
                modifier = Modifier.size(70.dp),
                contentScale = ContentScale.FillHeight,
                contentDescription = "item image"
            )

            Row(Modifier.padding(top = 16.dp, bottom = 16.dp, start = 5.dp)) {
                Column(Modifier.fillMaxWidth(.6f)) {
                    Text(
                        "Laboratory",
                        style = text14SB,
                        modifier = Modifier.padding(start = 6.dp),
                        color = Color.Black,
                        overflow = TextOverflow.Ellipsis
                    )

                    Row(
                        Modifier.padding(top = 4.dp, start = 6.dp, end = 6.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(
                            modifier = Modifier.size(16.dp),
                            painter = painterResource(R.drawable.ic_location),
                            contentScale = ContentScale.Fit,
                            contentDescription = "item image"
                        )
                        Text(
                            "Gwarinpa",
                            style = text12N,
                            modifier = Modifier.padding(start = 4.dp),
                            overflow = TextOverflow.Ellipsis
                        )
                    }


                }

                Column {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Image(
                            modifier = Modifier.size(16.dp),
                            painter = painterResource(R.drawable.ic_calendar),
                            contentScale = ContentScale.Fit,
                            contentDescription = "item image"
                        )
                        Text(
                            "20-05-2024",
                            style = text12N,
                            modifier = Modifier.padding(start = 4.dp),
                            overflow = TextOverflow.Ellipsis
                        )
                    }

                    Text(
                        "More Details",
                        style = text12N,
                        modifier = Modifier.padding(start = 4.dp, top = 5.dp),
                        overflow = TextOverflow.Ellipsis
                    )
                }

            }


        }

    }


}







