package controllers.controllerHelpers;

import database.WorldCreatorDatabase;

abstract class ControllerHelper {
    WorldCreatorDatabase database;

    public ControllerHelper() {
        this.database = new WorldCreatorDatabase();
    }
}
