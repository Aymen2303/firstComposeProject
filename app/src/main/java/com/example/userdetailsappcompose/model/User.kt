import android.os.Parcelable
import androidx.compose.ui.layout.Placeable
import com.google.gson.annotations.SerializedName
import com.google.type.Date
import com.google.type.DateTime
import java.text.DateFormat
import java.time.LocalDateTime
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

data class ApiResponse(
    val results: List<User>
)

data class User(
    val name: Name,
    val email: String,
    val location: Location,
    val picture: Picture,
    @SerializedName("phone")
    val phoneNumber: String,
    val dob: Dob
)

data class Name(
    val first: String,
    val last: String
)

data class Location(
    val country: String
)

data class Picture(
    val large: String
)

data class Dob(
    val date: String
)

fun toDate(wrongDate: String): String{
    val formatter = DateTimeFormatter.ISO_DATE_TIME
    val outputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
    val parsedDate = ZonedDateTime.parse(wrongDate, formatter)
    return parsedDate.format(outputFormatter)
}