package net.tisue.euler

// How many Sundays fell on the first of the month during the
// twentieth century (1 Jan 1901 to 31 Dec 2000)?

class Problem19 extends Problem(19, "171") {
  def solve = {
    import java.util.Calendar
    // The Java APIs for this are imperative so it's a bit awkward
    val calendar = Calendar.getInstance
    calendar.setTime(new java.text.SimpleDateFormat("MMM d yyyy").parse("Jan 1 1901"))
    Stream.iterate(calendar){cal => val newCal = cal.clone.asInstanceOf[Calendar]
                                    newCal.add(java.util.Calendar.MONTH,1)
                                    newCal}
      .take(1200).count(_.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY)
  }
}
