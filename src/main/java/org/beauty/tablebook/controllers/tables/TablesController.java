package org.beauty.tablebook.controllers.tables;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.beauty.tablebook.models.tables.TableDTO;
import org.beauty.tablebook.models.tables.TableService;
import org.beauty.tablebook.models.tables.Tables;
import org.beauty.tablebook.models.tables_version.TableVersionRepository;
import org.beauty.tablebook.models.tables_version.TablesVersion;
import org.beauty.tablebook.models.tables_version.TablesVersionsService;
import org.beauty.tablebook.models.users.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/restaurants/{id}/tables")
@CrossOrigin(origins = {
        "http://45.151.144.194:3000",
        "http://45.151.144.194:3000",
        "http://45.151.144.194:80"}
)

@Tag(name = "Работа со столами", description = "Столы")
@AllArgsConstructor
public class TablesController {

    @Autowired
    private final TableService tableService;

    @Autowired
    private final TablesVersionsService tablesVersionsService;

//    @GetMapping
//    @Operation
//    public ResponseEntity<List<TableDTO>> getTablesSchemeByRestaurantIDAndVersion(@PathVariable(name = "id") Long ID){
//
//        List<TableDTO> allTablesByRestaurantID = tableService.getAllTablesByRestaurantIDAndVersionID(ID);
//
//        return ResponseEntity.of(Optional.ofNullable(allTablesByRestaurantID));
//
//    }

//    @GetMapping
//    @Operation
//    public ResponseEntity<List<TablesVersion>> getTablesVersionByRestaurantID(@PathVariable(name = "id") Long restID){
//
//        List<TablesVersion> allTablesSchemeVersions = tablesVersionsService.getAllVersionByRestID(restID);
//
//        return ResponseEntity.ok(allTablesSchemeVersions);
//    }

//    @GetMapping(value = "/tables")
//    @Operation
//    public ResponseEntity<List<TableDTO>> getTablesByVersionID(@PathVariable(name = "versionID") Long versionID,
//                                                               @PathVariable String id){
//
//        List<TableDTO> tablesDTOs = tableService.getAllTablesByVersionID(versionID);
//
//        return ResponseEntity.ok(tablesDTOs);
//
//
//    }

    @PutMapping(value = "/{tableID}")
    @Operation
    public ResponseEntity<HttpStatus> putUpdateToTableByRestaurantIDAndTableID(
            @PathVariable(name = "id") Long restID,
            @PathVariable(name = "tableID") Long tableID,
            TableDTO tableDTO){

        Tables table = Tables.fromDTO(tableDTO);

        table.setId(tableID);

        tableService.saveTableToRestaurant(table, restID);


        return ResponseEntity.ok(HttpStatus.OK);

    }

}
