package com.linkedlist.pagingstudy

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.paging.compose.collectAsLazyPagingItems
import com.linkedlist.pagingstudy.ui.theme.PagingStudyTheme

class MainActivity : ComponentActivity() {
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PagingStudyTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ExampleList(viewModel = viewModel)
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExampleList(
    modifier: Modifier = Modifier,
    viewModel: MainViewModel,
) {
    val list = viewModel.documentsFlow.collectAsLazyPagingItems()
    val keyword by viewModel.keyword.collectAsState()

    Column {
        TextField(value = keyword, onValueChange = viewModel::updateKeyword)

        LazyColumn {
            items(list.itemCount) { item ->
                Box(modifier = Modifier.padding(horizontal = 16.dp, vertical = 12.dp)) {
                    Text(list[item]!!.title, fontSize = 16.sp, fontWeight = FontWeight.Bold)
                }
            }
        }
    }
}
