/*
 * Copyright (c) 2017 RWTH Aachen. All rights reserved.
 *
 * http://www.se-rwth.de/
 */
package bumperbot.test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Collection;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import de.monticore.lang.montiarc.montiarc._ast.ASTMontiArcNode;
import de.monticore.lang.montiarc.montiarc._symboltable.ComponentInstanceSymbol;
import de.monticore.lang.montiarc.montiarc._symboltable.ComponentSymbol;
import de.monticore.lang.montiarc.montiarcautomaton._cocos.MontiArcAutomatonCoCoChecker;
import de.monticore.lang.montiarc.montiarcautomaton.cocos.MontiArcAutomatonCocos;
import de.monticore.symboltable.Scope;
import de.se_rwth.commons.logging.Log;

public class ParserTest extends AbstractSymtabTest {

  @BeforeClass
  public static void setUp() {
    Log.enableFailQuick(false);
  }
  
  @Test
  public void testParseBumperbot() {
    ASTMontiArcNode node = getAstNode("src/main/resources/", "de.montiarcautomaton.lejos.BumperBot");
    assertNotNull(node);
    MontiArcAutomatonCoCoChecker checker = MontiArcAutomatonCocos.createChecker();
    checker.checkAll(node);
    assertTrue(Log.getFindings().toString(), Log.getFindings().isEmpty());
  }
  
  
  protected static ASTMontiArcNode getAstNode(String modelPath, String model) {
    // ensure an empty log
    Log.getFindings().clear();
    
    Scope symTab = createSymTab(modelPath);
    ComponentSymbol comp = symTab.<ComponentSymbol> resolve(model, ComponentSymbol.KIND)
        .orElse(null);
    Collection<ComponentInstanceSymbol> subcomps = comp.getSubComponents();
    
    assertNotNull("Could not resolve model " + model, comp);
    
    return (ASTMontiArcNode) comp.getAstNode().get();
  }
}
