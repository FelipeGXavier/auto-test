package core.command;

import core.service.MergeColumnService;
import core.service.ReadCsvService;

import java.util.Map;

public class TransformCsvCommand implements Command<Map<String, String>> {

    private ReadCsvService readCsvService;
    private MergeColumnService mergeColumnService;
    private String filename;

    public TransformCsvCommand(
            ReadCsvService readCsvService, MergeColumnService mergeColumnService, String filename) {
        this.readCsvService = readCsvService;
        this.mergeColumnService = mergeColumnService;
        this.filename = filename;
    }

    @Override
    public Map<String, String> execute() {
        try {
            String csv = this.readCsvService.readBin(this.filename);
            return this.mergeColumnService.mergeColumn(csv);
        } catch (Exception e) {
            return null;
        }
    }
}
