@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.firstcomposeproject

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModelProvider
import com.example.firstcomposeproject.ui.theme.FirstComposeProjectTheme
import com.example.firstcomposeproject.ui.theme.InstagramProfileCard

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        setContent {
            FirstComposeProjectTheme {
                Column(
                    modifier = Modifier
                        .background(MaterialTheme.colorScheme.background)
                        .fillMaxSize()
                ) {
                    InstagramProfileCard(viewModel)
                }
//                ScaffoldTest()
            }

        }
    }
}

data class NavItem(
    val title: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector
)

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
private fun ScaffoldTest() {

    val listItems = listOf(
        NavItem("Favorite", Icons.Filled.Favorite, Icons.Outlined.Favorite),
        NavItem("Edit", Icons.Filled.Edit, Icons.Outlined.Edit),
        NavItem("Delete", Icons.Filled.Delete, Icons.Outlined.Delete),
    )
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    var selectedItemIndex by remember { mutableIntStateOf(0) }
    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet(
            ) {
                Column(Modifier.verticalScroll(rememberScrollState())) {
                    listItems.forEachIndexed() { index, navItem ->
                        NavigationDrawerItem(
                            modifier = Modifier.width(150.dp),
                            label = { Text(text = navItem.title) },
                            selected = selectedItemIndex == index,
                            icon = {
                                Icon(
                                    imageVector = if (selectedItemIndex == index) navItem.selectedIcon
                                    else navItem.unselectedIcon, contentDescription = navItem.title
                                )
                            },
                            onClick = { selectedItemIndex = index })
                    }
                }
            }
        }
    ) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text(text = "TopAppBar") },
                    navigationIcon = {
                        IconButton(onClick = { }) {
                            Icon(Icons.Filled.Menu, contentDescription = null)
                        }
                    },
                    modifier = Modifier.background(Color.Cyan)
                )
            },
            bottomBar = {
                NavigationBar {
                    NavigationBarItem(selected = true, onClick = { },
                        icon = { Icon(Icons.Filled.ShoppingCart, contentDescription = null) },
                        label = { Text("Songs") }
                    )
                    NavigationBarItem(selected = true, onClick = { },
                        icon = { Icon(Icons.Filled.Favorite, contentDescription = null) },
                        label = { Text("Artists") }
                    )
                    NavigationBarItem(selected = true, onClick = { },
                        icon = { Icon(Icons.Filled.List, contentDescription = null) },
                        label = { Text("Playlists") }
                    )
                    //                items.forEachIndexed { index, item ->
                    //                    NavigationBarItem(
                    //                        icon = { Icon(Icons.Filled.Favorite, contentDescription = item) },
                    //                        label = { Text(item) },
                    //                        selected = selectedItem == index,
                    //                        onClick = { selectedItem = index }
                    //                    )
                    //                }
                }
            }
        ) {
            Column(
                modifier = Modifier
                    .padding(it)

            ) {
                Text(
                    modifier = Modifier.padding(8.dp),
                    text = "This is Scaffold content"
                )
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun Test() {

    var presses by remember { mutableStateOf(0) }

    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                title = {
                    Text("Top app bar")
                }
            )
        },
        bottomBar = {
            BottomAppBar {
                Text(
                    modifier = Modifier
                        .fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    text = "Bottom app bar",
                )
            }
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { presses++ }) {
                Icon(Icons.Default.Add, contentDescription = "Add")
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            Text(
                modifier = Modifier.padding(8.dp),
                text = "Scaffold content"
            )
        }
    }
}


@Composable
fun TextTest() {
    Column {
        Text(
            text = "Hello World!",
            color = Color.White,
            fontSize = 24.sp,
            fontWeight = FontWeight.ExtraBold,
            fontStyle = FontStyle.Italic,
            fontFamily = FontFamily.Cursive,
            textDecoration = TextDecoration.combine(
                listOf(TextDecoration.LineThrough, TextDecoration.Underline)
            )
        )
        Text(
            buildAnnotatedString {
                withStyle(SpanStyle(fontWeight = FontWeight.Bold, color = Color.White)) {
                    append("Hello")
                }
                withStyle(
                    SpanStyle(
                        textDecoration = TextDecoration.Underline,
                        color = Color.White
                    )
                ) {
                    append(" ")
                }
                withStyle(
                    SpanStyle(
                        fontSize = 30.sp,
                        textDecoration = TextDecoration.LineThrough,
                        color = Color.White
                    )
                ) {
                    append("World!")
                }
            }
        )

    }
}


//@Preview
@Composable
fun Example3() {
    val openDialog = remember { mutableStateOf(true) }

    if (openDialog.value) {
        AlertDialog(
            onDismissRequest = {
                // Dismiss the dialog when the user clicks outside the dialog or on the back
                // button. If you want to disable that functionality, simply use an empty
                // onDismissRequest.
                openDialog.value = false
            },
            title = {
                Text(text = "Are you sure?")
            },
            text = {
                Text(text = "Do you want to delete this file?")
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        openDialog.value = false
                    }
                ) {
                    Text("Yes")
                }
            },
            dismissButton = {
                TextButton(
                    onClick = {
                        openDialog.value = false
                    }
                ) {
                    Text("No")
                }
            }
        )
    }
}

