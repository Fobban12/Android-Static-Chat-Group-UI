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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

const val MAIN_ROUTE = "Info"
const val NEWS_ROUTE = "AddNewsInfo"




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
    val navigation = rememberNavController()
    val userVM = viewModel<LoginAndRegister>()
    Scaffold(
        topBar = {Header(userVM)},
        content = { MainNavigation(navigation) },
        bottomBar = { Footer(navigation)})
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
        Text("Sky News",Modifier
            .padding(horizontal = 5.dp),
            fontWeight = FontWeight.Bold)

        Text("Searchbar here")
        Text("Logout", Modifier
            .clickable { Logout.logout() })
    }
}
@Composable
fun MainNavigation(navController: NavHostController)
{
    NavHost(navController = navController, startDestination = MAIN_ROUTE )
    {
        composable( route = MAIN_ROUTE ){ MainContentView() }
        composable( route = NEWS_ROUTE){ AddNewsComment() }
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
fun Footer(navController: NavHostController)
{
    Row(modifier = Modifier
        .fillMaxWidth()
        .background(Color.Cyan)
        .padding(20.dp)
        ,
        horizontalArrangement = Arrangement.SpaceEvenly) {
      Icon(painter = painterResource(id = R.drawable.ic_homefeed ),
          contentDescription ="Info",
          modifier = Modifier.clickable { navController.navigate(MAIN_ROUTE) })
        Icon(painter = painterResource(id = R.drawable.icon_add), contentDescription = "AddNewsInfo",
        Modifier.clickable { navController.navigate(NEWS_ROUTE)  })
    }


}

@Composable
fun AddNewsComment(){
    Text("Hello this is where you could add comment to a news text")
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
            label = { Text(text = "Password (More than 5 characters)") },
            visualTransformation = PasswordVisualTransformation()
        )
        OutlinedButton(onClick = { if (email.isNotEmpty() && password.isNotEmpty()){userVM.login(email,password)} else {println("Null or empty text fields")} }) {
            Text(text = "Login") }

        OutlinedButton(onClick = { if (email.isNotEmpty() && password.isNotEmpty()){userVM.register(email,password)}else {println("Null or empty text fields")}}) {
            Text(text = "Register") }

    }
}



