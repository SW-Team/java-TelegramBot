package milk.telegram.handler;

import milk.telegram.update.Update;

import java.util.List;

public interface Handler{

    void update(List<Update> list);

}
