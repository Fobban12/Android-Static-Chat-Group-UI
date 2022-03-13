package com.example.mpntproject


import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


@Composable
fun Mainview() {
    val userVM = viewModel<LoginAndRegister>()
    if (userVM.username.value.isEmpty()) {
        LoginView(userVM)
    }
    else
    {
        ScaffoldView()
    }

}




@Composable
fun ScaffoldView()
{
    val userVM = viewModel<LoginAndRegister>()
    Scaffold(
        topBar = {Header(userVM)},
        content = { MainContentView()},
        bottomBar = { Footer()})
}



@Composable
fun Header(Logout:LoginAndRegister)
{
    Row(modifier = Modifier
        .fillMaxWidth()
        .background(Color.Cyan)
        .padding(20.dp)
        ,
        horizontalArrangement = Arrangement.SpaceBetween


    ) {
        Text("News",Modifier
            .padding(horizontal = 5.dp),
            fontWeight = FontWeight.Bold)

        Text("Searchbar here")
        Text("Logout", Modifier
            .clickable { Logout.logout() })
    }
}
@Composable
fun MainContentView()
{
    Column() {
        Text("The 'content' should be here")
    }

}
@Composable
fun Footer()
{
    Row() {
        Text("The bottom bar or something should be here instead of this text")
    }


}



@Composable
fun LoginView(userVM: LoginAndRegister){

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .padding(5.dp)
            .height(500.dp)
            .width(500.dp)
            .border(width = 1.dp, Color.Black),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally,

        ) {
        Text("Login to access content or register to add user")
        OutlinedTextField(
            value = email ,
            onValueChange = { email = it },
            label = { Text(text = "Email") })
        OutlinedTextField(
            value = password ,
            onValueChange = { password = it },
            label = { Text(text = "Password (Must be longer than 6)") },
            visualTransformation = PasswordVisualTransformation()
        )
        OutlinedButton(onClick = { if (email.isNotEmpty() && password.isNotEmpty()){userVM.login(email,password)} else {println("Null or empty text fields")} }) {
            Text(text = "Login") }

        OutlinedButton(onClick = { if (email.isNotEmpty() && password.isNotEmpty()){userVM.register(email,password)}else {println("Null or empty text fields")}}) {
            Text(text = "Register") }

    }
}



