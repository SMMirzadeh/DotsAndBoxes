package DotsAndBoxes;

import User.User;

public class OwnersBox {

    private User User;
    private boolean isOwned;

    public void setOwned(boolean owned) {
        isOwned = owned;
    }

    public boolean isOwned() {
        return isOwned;
    }

    public void setUser(User user) {
        User = user;
    }

    public User getUser() {
        return User;
    }
}
