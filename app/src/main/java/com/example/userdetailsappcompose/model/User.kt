import com.google.gson.annotations.SerializedName

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