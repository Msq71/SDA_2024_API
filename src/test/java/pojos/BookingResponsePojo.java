package pojos;

public class BookingResponsePojo {
    private Integer bookingid;
    private BookingPojo booking;

    public BookingResponsePojo() {
    }

    public BookingResponsePojo(Integer bookingId, BookingPojo booking) {
        this.bookingid = bookingId;
        this.booking = booking;
    }

    public Integer getBookingid() {
        return bookingid;
    }

    public void setBookingid(Integer bookingid) {
        this.bookingid = bookingid;
    }

    public BookingPojo getBooking() {
        return booking;
    }

    public void setBooking(BookingPojo booking) {
        this.booking = booking;
    }

    @Override
    public String toString() {
        return "BookingResponsePojo{" +
                "bookingId=" + bookingid +
                ", booking=" + booking +
                '}';
    }
}
