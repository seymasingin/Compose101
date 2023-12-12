package com.seymasingin.compose101

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.google.gson.Gson
import com.seymasingin.compose101.data.Foods
import com.seymasingin.compose101.ui.theme.Compose101Theme
import com.skydoves.landscapist.glide.GlideImage

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Compose101Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = colorResource(id = R.color.background)
                ) {
                    PageRouter()
                }
            }
        }
    }
}

@Composable
fun PageRouter() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "home"){
        composable("home"){
            Home(navController= navController)
        }
        composable("detail/{food}", arguments = listOf(
            navArgument("food"){type= NavType.StringType}
        )){
            val json = it.arguments?.getString("food")
            val food = Gson().fromJson(json, Foods::class.java)
            Detail(food = food)
        }
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Home(navController: NavController) {

    val viewModel: MainActivityViewModel = viewModel()
    val foodList = viewModel.foodList.observeAsState(listOf())

    Scaffold (
        topBar = {
            TopAppBar(
                modifier = Modifier.height(40.dp),
                colors = topAppBarColors(
                    titleContentColor = colorResource(id = R.color.white),
                    containerColor = colorResource(id = R.color.pink)
                ),
                title = {
                    Text("Foods")
                }
            )
        }
    ){contentPadding ->
            LazyColumn(modifier = Modifier.padding(contentPadding)){
                items(
                    count = foodList.value!!.count(),
                    itemContent = {
                        val food = foodList.value!![it]
                        Card(modifier = Modifier
                            .padding(all = 5.dp)
                            .fillMaxWidth(),
                            colors = CardDefaults.cardColors(
                                containerColor = colorResource(id = R.color.cardColor)
                            )) {
                            Row(modifier = Modifier.clickable {
                                val foodJson = Gson().toJson(food)
                                navController.navigate("detail/$foodJson")
                            }) {
                                Row(verticalAlignment = Alignment.CenterVertically,
                                    modifier = Modifier
                                        .padding(all = 10.dp)
                                        .fillMaxWidth()
                                ) {
                                    GlideImage(
                                        imageModel = { "http://kasimadalan.pe.hu/yemekler/resimler/${food.image}" },
                                        modifier = Modifier.size(100.dp))
                                    Row(
                                        verticalAlignment = Alignment.CenterVertically,
                                        horizontalArrangement = Arrangement.SpaceBetween,
                                        modifier = Modifier.fillMaxWidth()
                                    ) {
                                        Column(
                                            modifier = Modifier.padding(start = 10.dp)
                                        ) {
                                            Text(text = food.name,fontSize = 20.sp, fontWeight = FontWeight.Bold)
                                            Text(text = "${food.price} ₺",
                                                fontSize = 18.sp,
                                                color = colorResource(id = R.color.priceColor),
                                                fontWeight = FontWeight.Bold)
                                        }
                                        Icon(painter = painterResource(id = R.drawable.arrow),
                                            contentDescription = "")
                                    }
                                }
                            }
                        }
                    }
                )
            }
        }
    }



@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Compose101Theme {

    }
}