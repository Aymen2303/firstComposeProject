package Model

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class User(
    val name: String,
    val email: String,
    val country: String,
    val pictureUrl: String,
    val phoneNumber: String,
    val dateOfBirth: String
) {
    companion object {
        fun fromJson(json: Map<String, Any>): User {
            val name = "${(json["name"] as Map<String, String>)["first"]} ${(json["name"] as Map<String, String>)["last"]}"
            val email = json["email"] as String
            val country = (json["location"] as Map<String, String>)["country"]?: ""
            val pictureUrl = (json["picture"] as Map<String, String>)["large"]?: ""
            val phoneNumber = json["phone"] as String
            val dateOfBirth = (json["dob"] as Map<String, String>)["date"]?: ""

            return User(
                name = name,
                email = email,
                country = country,
                pictureUrl = pictureUrl,
                phoneNumber = phoneNumber,
                dateOfBirth = dateOfBirth,
            )
        }
    }

    override fun toString(): String {
        return "User(name='$name', email='$email', dateOfBirth='$dateOfBirth', phoneNumber='$phoneNumber')"
    }

    fun toDate(wrongDate: String): String {
        val inputFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val outputFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val date: Date = inputFormat.parse(wrongDate) ?: return wrongDate
        return outputFormat.format(date)
    }
}
