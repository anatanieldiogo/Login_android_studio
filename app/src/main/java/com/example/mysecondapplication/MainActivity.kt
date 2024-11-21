package com.example.mysecondapplication

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.mysecondapplication.ui.theme.MySecondApplicationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MySecondApplicationTheme {
                Scaffold(
                    modifier = Modifier
                        .fillMaxSize()
                        .safeDrawingPadding()
                ) { innerPadding ->
                    
                    
                    val navController = rememberNavController()
                    NavHost(navController = navController, startDestination = "main"){
                        composable("main"){
                            AuthScreen(
                                onEnterClick = {
                                    Log.i("MainActivity", "onCreate: $it")
                                },
                                modifier = Modifier.padding(
                                    innerPadding
                                )
                            )
                        }
                        composable("dashboard"){

                        }
                    }

                }
            }
        }
    }
}



@Composable
fun AuthScreen(onEnterClick: (User) -> Unit, modifier: Modifier = Modifier) {

    Column(
        Modifier
            .background(color = Color(0xFFFFFFFF))
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
    ) {
        var username by remember {
            mutableStateOf("")
        }

        var password by remember {
            mutableStateOf("")
        }

        Image(
            painter = painterResource(id = R.drawable.peace_peace), contentDescription = "Peace",
            Modifier
                .size(100.dp)
                .align(alignment = Alignment.CenterHorizontally)
        )

        Spacer(modifier = Modifier.size(30.dp))

        TextField(
            value = username, onValueChange = {
                username = it
            },
            Modifier
                .padding(8.dp)
                .fillMaxWidth(),
            label = {
                Text("Usuário")
            },
            leadingIcon = {
                Icon(imageVector = Icons.Default.AccountCircle, contentDescription = "User")
            }
        )

        TextField(
            value = password, onValueChange = {
                password = it
            },
            Modifier
                .padding(8.dp)
                .fillMaxWidth(), visualTransformation = PasswordVisualTransformation(),
            label = {
                Text("Senha")
            },
            leadingIcon = {
                Icon(imageVector = Icons.Default.Lock, contentDescription = "Password")
            }
        )

        Button(
            onClick = { onEnterClick(User(username, password)) },
            Modifier
                .padding(8.dp)
                .fillMaxWidth(),
        ) {
            Text(text = "Entrar")
        }
    }
}

@Composable
@Preview
fun AppPreview() {
    MySecondApplicationTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            AuthScreen(
                onEnterClick = {},
                modifier = Modifier.padding(
                    innerPadding
                )
            )
        }
    }
}