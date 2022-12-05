package com.example.fit.ui.Login

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.AbsoluteRoundedCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.fit.R
import com.example.fit.ui.navigation.Screen
import com.example.fit.ui.theme.yellowdeep
import com.example.fit.viewmodel.ModelLogin

@Composable
fun loginscreen(navController: NavController) {
    val viewModel = hiltViewModel<ModelLogin>()
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.Unspecified)
    )
    {
        Image(
            modifier = Modifier.fillMaxWidth().fillMaxHeight()
                .blur(2.dp),
            painter =  painterResource(id = R.drawable.img),
            contentDescription = "image",
            contentScale = ContentScale.Crop
        )
        Box(modifier = Modifier.align(Alignment.BottomCenter))
        {
            contentlogin(viewModel,navController,"Welcome Back", "Ananda")
        }

    }


}

@Composable
fun contentlogin(
    viewModel:ModelLogin,
    navController:NavController,
    content1: String,
    content2: String
) {
    var email by remember {
        mutableStateOf("")
    }
    var password by remember {
        mutableStateOf("")
    }
    var passwordconfirm by remember {
        mutableStateOf("")
    }
    var passwordvisible by remember {
        mutableStateOf(true)
    }
    var iconPassword=if(passwordvisible)
        painterResource(id = R.drawable.ic_baseline_visibility_24)
    else
        painterResource(id = R.drawable.ic_baseline_visibility_off_24)
    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(40.dp, 5.dp),
            horizontalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            Text(
                text = "Login",
                color = MaterialTheme.colorScheme.yellowdeep,
                fontWeight = FontWeight.Bold,
                fontSize = 30.sp,

            )
            Divider(
                color = Color.White, modifier = Modifier
                    .width(5.dp)
                    .height(40.dp)
            )
            Text(text = "Signup", color = Color.DarkGray, fontSize = 30.sp,
                modifier = Modifier.clickable {   navController.navigate(route= Screen.SignUp.router) })
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(.7f)
                .padding(20.dp)
                .clip(shape = RoundedCornerShape(36.dp))
        )
        {
            Surface(
                color = Color.White,
                modifier = Modifier
                    .fillMaxSize()
                    .alpha(0.5f)
            ) {
                Column(
                    modifier = Modifier.padding(30.dp),
                    verticalArrangement = Arrangement.spacedBy(20.dp)

                ) {
                    Column {
                        Text(text = content1)
                        Text(text = content2)
                    }
                    Column(
                        modifier = Modifier
                            .fillMaxWidth(),
                        verticalArrangement = Arrangement.spacedBy(20.dp),
                    ) {
                        OutlinedTextField(
                            value = email,
                            onValueChange = {
                                email = it
                            },
                            label = {
                                Text(text = "Email")
                            },
                            trailingIcon = {
                                IconButton(onClick = {}) {
                                    Icon(
                                        imageVector = Icons.Filled.Email,
                                        contentDescription = "Email icon"
                                    )
                                }
                            },
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(25.dp),
                        )
                        OutlinedTextField(
                            value = password,
                            onValueChange = {
                                password = it
                            },
                            label = {
                                Text(text = "Password")
                            },
                            trailingIcon = {
                                IconButton(onClick = {passwordvisible=!passwordvisible}) {
                                    Icon(
                                        painter= iconPassword,
                                        contentDescription = "password icon"
                                    )
                                }
                            },
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(25.dp),
                            visualTransformation = if(passwordvisible) VisualTransformation.None
                             else PasswordVisualTransformation()
                        )

                        Row(
                            horizontalArrangement = Arrangement.End,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            ClickableText(text = AnnotatedString("Forgot password ?"), onClick = {

                            })
                            Spacer(modifier = Modifier.width(10.dp))
                           ClickableText(text = AnnotatedString("Don't have account ?"), onClick = {
                               navController.navigate(route= Screen.SignUp.router)
                           })
                        }


                    }
                    Button(
                        onClick = { Log.d("logintest","d")
                            viewModel.login(email,password)
                                  }, modifier = Modifier.fillMaxWidth().background(Color.White),
                        shape = AbsoluteRoundedCornerShape(50, 100, 10, 50)
                    ) {
                        Text(text = "Login", color = Color.Blue)
                    }
                }
            }
        }

    }
}