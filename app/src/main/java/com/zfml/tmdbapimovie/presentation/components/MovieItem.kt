package com.zfml.tmdbapimovie.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ImageNotSupported
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Size
import com.ahmedapps.moviesapp.movieList.util.RatingBar
import com.zfml.tmdbapimovie.domain.model.Movie
import com.zfml.tmdbapimovie.util.Constants.IMAGE_BASE_URL

@Composable
fun MovieItem(
    movie: Movie
) {
    val imageState = rememberAsyncImagePainter(
        model =ImageRequest.Builder(LocalContext.current)
            .data(IMAGE_BASE_URL+movie.backdropPath)
            .size(Size.ORIGINAL)
            .build()
    ).state

    Surface (
        modifier = Modifier
            .width(200.dp)
            .padding(8.dp)
            .wrapContentHeight()
        ,
        tonalElevation = 3.dp,
        shape = Shapes().medium,
        color = MaterialTheme.colorScheme.secondary
    ){
        Column(
            modifier = Modifier
        ){
            if(imageState is AsyncImagePainter.State.Error) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(6.dp)
                        .height(250.dp),
                    contentAlignment = Alignment.Center
                ){
                    Icon(
                        modifier = Modifier.size(70.dp),
                        imageVector = Icons.Rounded.ImageNotSupported,
                        contentDescription =  movie.title
                    )
                }

            }

            if(imageState is AsyncImagePainter.State.Success) {
                Image(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(250.dp),
                    painter = imageState.painter,
                    contentDescription = movie.title,
                    contentScale = ContentScale.Crop
                )
            }
            Spacer(modifier = Modifier.height(6.dp))

            Text(
                modifier = Modifier.padding(8.dp),
                text = movie.title,
                style = TextStyle(
                    fontSize = MaterialTheme.typography.titleMedium.fontSize,
                ),
                maxLines = 1
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            ){
                RatingBar(
                    starsModifier = Modifier.size(18.dp),
                    rating = movie.voteAverage / 2
                )
                Text(
                    modifier = Modifier
                        .padding(start = 4.dp)
                    ,
                    text = movie.voteAverage.toString().take(3),
                    maxLines = 1,
                    style = TextStyle(
                        fontSize = MaterialTheme.typography.bodySmall.fontSize
                    )
                )


            }

        }
    }


}