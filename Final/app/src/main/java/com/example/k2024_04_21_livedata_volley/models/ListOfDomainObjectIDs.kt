package  com.example.k2024_04_21_livedata_volley.models

object ListOfDomainObjectIDs {
    val allIds = mutableListOf<DomainObjectId>()

    fun getAllMyIds() : List<DomainObjectId> {
        return allIds
    }

    fun size() : Int {
        return allIdInts.size
    }

    val allIdInts = (0..600).toList()

    init{
        allIdInts.forEach{ myInt ->
            allIds.add(DomainObjectId(myInt))
        }
    }

}