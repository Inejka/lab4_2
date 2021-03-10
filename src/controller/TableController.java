package controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import support.IntShell;
import support.MyVoid;
import view.tableComponents.MyTableView;

public abstract class TableController {
    protected final IntShell currentPage = new IntShell(1, Integer.MAX_VALUE);
    protected  IntShell maxViewsPerPage;
    protected final MyTableView tableView;
    protected final Pane parent;

    private final MyVoid updateTable = this::updateTable;

    public MyVoid getUpdateTable() {
        return updateTable;
    }

    final EventHandler<ActionEvent> jumpStart = e -> {
        currentPage.setNumber(1);
        updateTable();
    };

    public EventHandler<ActionEvent> getJumpStart() {
        return jumpStart;
    }

    public EventHandler<ActionEvent> getJumpEnd() {
        return jumpEnd;
    }

    final EventHandler<ActionEvent> jumpEnd = e -> {
        if (maxViewsPerPage.getNumber() > getCount()) currentPage.setNumber(1);
        else
            currentPage.setNumber(getCount() / maxViewsPerPage.getNumber() + 1);
        updateTable();
    };

    public IntShell getCurrentPage() {
        return currentPage;
    }

    public IntShell getMaxViewsPerPage() {
        return maxViewsPerPage;
    }

    public TableController(int maxViewsPerPage, int currentMaxViewsPerPage, MyTableView tableView, Pane parent) {
        this.maxViewsPerPage = new IntShell(currentMaxViewsPerPage, maxViewsPerPage);
        this.tableView = tableView;
        this.parent = parent;
    }

    public Pane getTableParent() {
        return parent;
    }

    public void updateTable() {
        tableView.clear();
        for (int i = 0; i < getPageCount(); i++)
            tableView.getChildren().add(getView(i));
    }

    abstract int getPageCount();

    abstract int getCount();

    abstract Region getView(int pos);

}
