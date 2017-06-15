package com.vp.plugin.sample.reloadclasses.actions;

import java.util.List;

import com.vp.plugin.ApplicationManager;
import com.vp.plugin.ProjectManager;
import com.vp.plugin.connectors.domainmodel.VPDomainModelConnector;
import com.vp.plugin.connectors.domainmodel.br.DomainModelSBVRRelevantElementsContainer;
import com.vp.plugin.model.IBRKeyword;
import com.vp.plugin.model.IBRKeywords;
import com.vp.plugin.model.IBusinessRule;
import com.vp.plugin.model.IClass;
import com.vp.plugin.model.IFact;
import com.vp.plugin.model.IFactRole;
import com.vp.plugin.model.IGlossaryTermAttribute;
import com.vp.plugin.model.IModel;
import com.vp.plugin.model.IModelElement;
import com.vp.plugin.model.ITerm;
import com.vp.plugin.model.factory.IModelElementFactory;

public class AllVpModelsChecker {

	public static void checkSelected() {

		VPDomainModelConnector connector = new VPDomainModelConnector();

		DomainModelSBVRRelevantElementsContainer container = connector.fetchSBVRRelevantElements();
		// List<Relationship> r = connector.fetchRelationships();
		// List<Term> t = connector.fetchTerms();
		// TermsAndClassFacts tasf = connector.fetchTermsAndSimpleFacts();

		ProjectManager projectManager = ApplicationManager.instance().getProjectManager();
		IModelElement[] models3 = projectManager.getProject()
				.toAllLevelModelElementArray(IModelElementFactory.MODEL_TYPE_BR_KEYWORDS);

		for (IModelElement elem : models3) {
			IBRKeywords keywords = (IBRKeywords) elem;

			for (IBRKeyword kw : keywords.toBRKeywordArray()) {
				System.out.println(kw.getName());
			}
		}

		models3 = projectManager.getProject().toAllLevelModelElementArray(IModelElementFactory.MODEL_TYPE_TERM);

		System.out.println("############## MODELS ...");

		for (IModelElement elem : models3) {
			IModel m = (IModel) elem;

			for (IModelElement el : m.toChildArray()) {
				System.out.println("MODEL ELEMENT: " + el.getName());
			}

		}

		models3 = projectManager.getProject()
				.toAllLevelModelElementArray(IModelElementFactory.MODEL_TYPE_GLOSSARY_TERM_ATTRIBUTE);

		System.out.println("############## MODELS ...");

		for (IModelElement elem : models3) {
			IGlossaryTermAttribute m = (IGlossaryTermAttribute) elem;

			// for (IModelElement el : m.toChildArray()) {
			// System.out.println("MODEL ELEMENT: " + el.getName());
			// }

		}

		// models3 =
		// projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_BP_POOL);
		//
		// System.out.println("############## BP TASKS ...");
		//
		// for (IModelElement elem : models3) {
		// IBPPool pool = (IBPPool) elem;
		//
		// IBPTask[] bptasks = pool.toBPTaskArray();
		// for (IBPTask bptask : bptasks) {
		// System.out.println(bptask.getName());
		// }
		// }
		//
		// models3 =
		// projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_CLASS);
		//
		// System.out.println("############## CLASSES ...");
		//
		// for (IModelElement elem : models3) {
		// IClass c = (IClass) elem;
		//
		// for (IRelationshipEnd rend : c.toToRelationshipEndArray()) {
		//
		// IEndRelationship end = rend.getEndRelationship(); // relacja
		// IRelationshipEnd oppend = rend.getOppositeEnd();
		//
		// IModelElement end1 = end.getTo(); // class1
		// IModelElement end2 = end.getFrom();// class2
		//
		// String sss = rend.getName();
		//
		// System.out.println("here");
		// }
		//
		// System.out.println("CLASS: " + c.getName());
		//
		// }
		//
		models3 = projectManager.getProject().toAllLevelModelElementArray(IModelElementFactory.MODEL_TYPE_MODEL);

		System.out.println("############## MODELS ...");

		for (IModelElement elem : models3) {
			IModel m = (IModel) elem;

			String kindOfModel = m.getName();

			if (kindOfModel.equals("Business Rules")) {

				IModelElement[] businessRules = m.toChildArray();
				for (IModelElement br : businessRules) {
					System.out.println("BR: " + br.getName());

					IBusinessRule ibr = (IBusinessRule) br;

					System.out.println(ibr.getRuleText());
					ITerm[] brTerms = (ITerm[]) ibr.toTermArray();
					IFact[] brFacts = (IFact[]) ibr.toFactArray();

					if (brTerms != null) {
						for (ITerm t : ibr.toTermArray()) {
							System.out.println(br.getName() + " TERM: " + t.getName());
						}
					}

					if (brFacts != null) {
						for (IFact f : ibr.toFactArray()) {
							System.out.println(br.getName() + " FACT: " + f.getName());

							IFactRole[] factRoles = f.toFactRoleArray();
							if (factRoles != null) {
								for (IFactRole fr : factRoles) {
									System.out.println(br.getName() + " FACT role: " + fr.getName());
								}
							}

						}
					}

				}

			}

			if (kindOfModel.equals("Glossary")) {
				IModelElement[] glossaryElements = m.toChildArray();
				for (IModelElement g : glossaryElements) {
					System.out.println("Gl elem: " + g.getName());
				}
			}

			// System.out.println(m.getName());

			// if (m.getName().equals("Business rules")) {
			// for (IModelElement el : m.toChildArray()) {
			// System.out.println("MODEL ELEMENT: " + el.getName());
			// // if (el instanceof IBusinessRule) {
			// // IBusinessRule br = (IBusinessRule) el;
			// // String text = br.getRuleText();
			// // System.out.println(" text - " + text);
			// // for (IFact f : br.toFactArray()) {
			// // System.out.println(" fact - " + f.getName());
			// // }
			// // for (ITerm t : br.toTermArray()) {
			// // System.out.println(" term - " + t.getName());
			// // }
			// // }
			// }
			// }

			// if (m.getName().equals("Business rules")) {
			// for (IModelElement el : m.toChildArray()) {
			// System.out.println("MODEL ELEMENT: " + el.getName());
			// }
			// }

			// if (m.getName().equals("Glossary")) {
			// for (IModelElement el : m.toChildArray()) {
			// System.out.println("MODEL ELEMENT: " + el.getName());
			// }
			// }

		}
		//
		// models3 =
		// projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_TERM);
		//
		// System.out.println("############## MODELS ...");
		//
		// for (IModelElement elem : models3) {
		// IModel m = (IModel) elem;
		//
		// for (IModelElement el : m.toChildArray()) {
		// System.out.println("MODEL ELEMENT: " + el.getName());
		// }
		//
		// }
		//
		// models3 =
		// projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_PACKAGE);
		//
		// List<String> acc = new ArrayList<>();
		// for (IModelElement elem : models3) {
		// fetchAllClassesInProject(elem, acc);
		// }
		//
		// for (String cl : acc) {
		// System.out.println("ACC CLASS: " + cl);
		// }
		//
		// models3 =
		// projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_GLOSSARY);
		//
		// for (IModelElement elem : models3) {
		// IGlossary g = (IGlossary) elem;
		//
		// g.toTermArray();
		//
		// }
		//
		// models3 = projectManager.getProject()
		// .toModelElementArray(IModelElementFactory.MODEL_TYPE_MODEL_RELATIONSHIP_CONTAINER);
		//
		// for (IModelElement elem : models3) {
		//
		// IRelationshipEnd[] rend = elem.toToRelationshipEndArray();
		// System.out.println(rend[0].getName());
		// }

		// System.out.println("############## CLASSES ...");
		//
		// for (IModelElement elem : models3) {
		// IModel m = (IModel) elem;
		//
		// for (IModelElement el : m.toChildArray()) {
		// System.out.println("MODEL ELEMENT: " + el.getName());
		// }
		//
		// }

	}

	static void fetchAllClassesInProject(IModelElement el, List<String> accumulator) {
		for (IModelElement ch : el.toChildArray()) {
			if (ch instanceof IClass) {
				accumulator.add(ch.getName());
				System.out.println("PACKAGE EL: " + ch.getName());
			}
			fetchAllClassesInProject(ch, accumulator);
		}
	}

	public static void checkAllModels() {

		checkSelected();

		ProjectManager projectManager = ApplicationManager.instance().getProjectManager();
		IModelElement[] models3 = projectManager.getProject()
				.toModelElementArray(IModelElementFactory.MODEL_TYPE_ABSTRACTION);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_ABSTRACTION);
		System.out.print(models3.length == 0 ? "" : "\n" + "Abstraction " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_ACCEPT_EVENT_ACTION);
		System.out.print(models3.length == 0 ? "" : "\n" + "AcceptEventAction " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_ACTION);
		System.out.print(models3.length == 0 ? "" : "\n" + "Action " + models3.length);

		models3 = projectManager.getProject()
				.toModelElementArray(IModelElementFactory.MODEL_TYPE_ACTION_SCRIPT_ATTRIBUTE_CODE_DETAIL);
		System.out.print(models3.length == 0 ? "" : "\n" + "ActionScriptAttributeCodeDetail " + models3.length);

		models3 = projectManager.getProject()
				.toModelElementArray(IModelElementFactory.MODEL_TYPE_ACTION_SCRIPT_CLASS_CODE_DETAIL);
		System.out.print(models3.length == 0 ? "" : "\n" + "ActionScriptClassCodeDetail " + models3.length);

		models3 = projectManager.getProject()
				.toModelElementArray(IModelElementFactory.MODEL_TYPE_ACTION_SCRIPT_OPERATION_CODE_DETAIL);
		System.out.print(models3.length == 0 ? "" : "\n" + "ActionScriptOperationCodeDetail " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_ACTION_STATE);
		System.out.print(models3.length == 0 ? "" : "\n" + "ActionState " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_ACTION_TYPE_CALL);
		System.out.print(models3.length == 0 ? "" : "\n" + "ActionTypeCall " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_ACTION_TYPE_CREATE);
		System.out.print(models3.length == 0 ? "" : "\n" + "ActionTypeCreate " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_ACTION_TYPE_DESTROY);
		System.out.print(models3.length == 0 ? "" : "\n" + "ActionTypeDestroy " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_ACTION_TYPE_RETURN);
		System.out.print(models3.length == 0 ? "" : "\n" + "ActionTypeReturn " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_ACTION_TYPE_SEND);
		System.out.print(models3.length == 0 ? "" : "\n" + "ActionTypeSend " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_ACTION_TYPE_SEQUENCE);
		System.out.print(models3.length == 0 ? "" : "\n" + "ActionTypeSequence " + models3.length);

		models3 = projectManager.getProject()
				.toModelElementArray(IModelElementFactory.MODEL_TYPE_ACTION_TYPE_TERMINATE);
		System.out.print(models3.length == 0 ? "" : "\n" + "ActionTypeTerminate " + models3.length);

		models3 = projectManager.getProject()
				.toModelElementArray(IModelElementFactory.MODEL_TYPE_ACTION_TYPE_UNINTERPRETED);
		System.out.print(models3.length == 0 ? "" : "\n" + "ActionTypeUninterpreted " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_ACTIVATION);
		System.out.print(models3.length == 0 ? "" : "\n" + "Activation " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_ACTIVITY);
		System.out.print(models3.length == 0 ? "" : "\n" + "Activity " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_ACTIVITY_ACTION);
		System.out.print(models3.length == 0 ? "" : "\n" + "ActivityAction " + models3.length);

		models3 = projectManager.getProject()
				.toModelElementArray(IModelElementFactory.MODEL_TYPE_ACTIVITY_EDGE_CONNECTOR);
		System.out.print(models3.length == 0 ? "" : "\n" + "ActivityEdgeConnector " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_ACTIVITY_FINAL_NODE);
		System.out.print(models3.length == 0 ? "" : "\n" + "ActivityFinalNode " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_ACTIVITY_OBJECT);
		System.out.print(models3.length == 0 ? "" : "\n" + "ActivityObject " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_ACTIVITY_OBJECT_FLOW);
		System.out.print(models3.length == 0 ? "" : "\n" + "ActivityObjectFlow " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_ACTIVITY_PARAMETER);
		System.out.print(models3.length == 0 ? "" : "\n" + "ActivityParameter " + models3.length);

		models3 = projectManager.getProject()
				.toModelElementArray(IModelElementFactory.MODEL_TYPE_ACTIVITY_PARAMETER_NODE);
		System.out.print(models3.length == 0 ? "" : "\n" + "ActivityParameterNode " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_ACTIVITY_PARTITION);
		System.out.print(models3.length == 0 ? "" : "\n" + "ActivityPartition " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_ACTIVITY_SWIMLANE2);
		System.out.print(models3.length == 0 ? "" : "\n" + "ActivitySwimlane2 " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_ACTOR);
		System.out.print(models3.length == 0 ? "" : "\n" + "Actor " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_ACTOR_GOAL);
		System.out.print(models3.length == 0 ? "" : "\n" + "ActorGoal " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_ALIAS);
		System.out.print(models3.length == 0 ? "" : "\n" + "Alias " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_ANALYSIS_COMPOSITION);
		System.out.print(models3.length == 0 ? "" : "\n" + "AnalysisComposition " + models3.length);

		models3 = projectManager.getProject()
				.toModelElementArray(IModelElementFactory.MODEL_TYPE_ANALYSIS_DIAGRAM_GROUP_NODE);
		System.out.print(models3.length == 0 ? "" : "\n" + "AnalysisDiagramGroupNode " + models3.length);

		models3 = projectManager.getProject()
				.toModelElementArray(IModelElementFactory.MODEL_TYPE_ANALYSIS_DIAGRAM_NODE);
		System.out.print(models3.length == 0 ? "" : "\n" + "AnalysisDiagramNode " + models3.length);

		models3 = projectManager.getProject()
				.toModelElementArray(IModelElementFactory.MODEL_TYPE_ANALYSIS_DIAGRAM_TRANSITOR);
		System.out.print(models3.length == 0 ? "" : "\n" + "AnalysisDiagramTransitor " + models3.length);

		models3 = projectManager.getProject()
				.toModelElementArray(IModelElementFactory.MODEL_TYPE_ANALYSIS_LOGICAL_VIEW);
		System.out.print(models3.length == 0 ? "" : "\n" + "AnalysisLogicalView " + models3.length);

		models3 = projectManager.getProject()
				.toModelElementArray(IModelElementFactory.MODEL_TYPE_ANALYSIS_LOGICAL_VIEW_GROUP_NODE);
		System.out.print(models3.length == 0 ? "" : "\n" + "AnalysisLogicalViewGroupNode " + models3.length);

		models3 = projectManager.getProject()
				.toModelElementArray(IModelElementFactory.MODEL_TYPE_ANALYSIS_LOGICAL_VIEW_NODE);
		System.out.print(models3.length == 0 ? "" : "\n" + "AnalysisLogicalViewNode " + models3.length);

		models3 = projectManager.getProject()
				.toModelElementArray(IModelElementFactory.MODEL_TYPE_ANALYSIS_MODEL_GROUP_NODE);
		System.out.print(models3.length == 0 ? "" : "\n" + "AnalysisModelGroupNode " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_ANALYSIS_MODEL_NODE);
		System.out.print(models3.length == 0 ? "" : "\n" + "AnalysisModelNode " + models3.length);

		models3 = projectManager.getProject()
				.toModelElementArray(IModelElementFactory.MODEL_TYPE_ANALYSIS_PARENT_CHILD);
		System.out.print(models3.length == 0 ? "" : "\n" + "AnalysisParentChild " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_ANALYSIS_REFERENCE);
		System.out.print(models3.length == 0 ? "" : "\n" + "AnalysisReference " + models3.length);

		models3 = projectManager.getProject()
				.toModelElementArray(IModelElementFactory.MODEL_TYPE_ANALYSIS_RELATIONSHIP);
		System.out.print(models3.length == 0 ? "" : "\n" + "AnalysisRelationship " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_ANALYSIS_SUB_DIAGRAM);
		System.out.print(models3.length == 0 ? "" : "\n" + "AnalysisSubDiagram " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_ANALYSIS_TRANSITOR);
		System.out.print(models3.length == 0 ? "" : "\n" + "AnalysisTransitor " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_ANALYSIS_USED);
		System.out.print(models3.length == 0 ? "" : "\n" + "AnalysisUsed " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_ANALYSIS_VIEW);
		System.out.print(models3.length == 0 ? "" : "\n" + "AnalysisView " + models3.length);

		models3 = projectManager.getProject()
				.toModelElementArray(IModelElementFactory.MODEL_TYPE_ANALYSIS_VIEW_GROUP_NODE);
		System.out.print(models3.length == 0 ? "" : "\n" + "AnalysisViewGroupNode " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_ANALYSIS_VIEW_NODE);
		System.out.print(models3.length == 0 ? "" : "\n" + "AnalysisViewNode " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_ANCHOR);
		System.out.print(models3.length == 0 ? "" : "\n" + "Anchor " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_ANY_TRIGGER);
		System.out.print(models3.length == 0 ? "" : "\n" + "AnyTrigger " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_ARCHI_MATE_ACCESS);
		System.out.print(models3.length == 0 ? "" : "\n" + "ArchiMateAccess " + models3.length);

		models3 = projectManager.getProject()
				.toModelElementArray(IModelElementFactory.MODEL_TYPE_ARCHI_MATE_AGGREGATION);
		System.out.print(models3.length == 0 ? "" : "\n" + "ArchiMateAggregation " + models3.length);

		models3 = projectManager.getProject()
				.toModelElementArray(IModelElementFactory.MODEL_TYPE_ARCHI_MATE_APPLICATION_COLLABORATION);
		System.out.print(models3.length == 0 ? "" : "\n" + "ArchiMateApplicationCollaboration " + models3.length);

		models3 = projectManager.getProject()
				.toModelElementArray(IModelElementFactory.MODEL_TYPE_ARCHI_MATE_APPLICATION_COMPONENT);
		System.out.print(models3.length == 0 ? "" : "\n" + "ArchiMateApplicationComponent " + models3.length);

		models3 = projectManager.getProject()
				.toModelElementArray(IModelElementFactory.MODEL_TYPE_ARCHI_MATE_APPLICATION_FUNCTION);
		System.out.print(models3.length == 0 ? "" : "\n" + "ArchiMateApplicationFunction " + models3.length);

		models3 = projectManager.getProject()
				.toModelElementArray(IModelElementFactory.MODEL_TYPE_ARCHI_MATE_APPLICATION_INTERACTION);
		System.out.print(models3.length == 0 ? "" : "\n" + "ArchiMateApplicationInteraction " + models3.length);

		models3 = projectManager.getProject()
				.toModelElementArray(IModelElementFactory.MODEL_TYPE_ARCHI_MATE_APPLICATION_INTERFACE);
		System.out.print(models3.length == 0 ? "" : "\n" + "ArchiMateApplicationInterface " + models3.length);

		models3 = projectManager.getProject()
				.toModelElementArray(IModelElementFactory.MODEL_TYPE_ARCHI_MATE_APPLICATION_SERVICE);
		System.out.print(models3.length == 0 ? "" : "\n" + "ArchiMateApplicationService " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_ARCHI_MATE_ARTIFACT);
		System.out.print(models3.length == 0 ? "" : "\n" + "ArchiMateArtifact " + models3.length);

		models3 = projectManager.getProject()
				.toModelElementArray(IModelElementFactory.MODEL_TYPE_ARCHI_MATE_ASSESSMENT);
		System.out.print(models3.length == 0 ? "" : "\n" + "ArchiMateAssessment " + models3.length);

		models3 = projectManager.getProject()
				.toModelElementArray(IModelElementFactory.MODEL_TYPE_ARCHI_MATE_ASSIGNMENT);
		System.out.print(models3.length == 0 ? "" : "\n" + "ArchiMateAssignment " + models3.length);

		models3 = projectManager.getProject()
				.toModelElementArray(IModelElementFactory.MODEL_TYPE_ARCHI_MATE_ASSOCIATION);
		System.out.print(models3.length == 0 ? "" : "\n" + "ArchiMateAssociation " + models3.length);

		models3 = projectManager.getProject()
				.toModelElementArray(IModelElementFactory.MODEL_TYPE_ARCHI_MATE_BUSINESS_ACTOR);
		System.out.print(models3.length == 0 ? "" : "\n" + "ArchiMateBusinessActor " + models3.length);

		models3 = projectManager.getProject()
				.toModelElementArray(IModelElementFactory.MODEL_TYPE_ARCHI_MATE_BUSINESS_COLLABORATION);
		System.out.print(models3.length == 0 ? "" : "\n" + "ArchiMateBusinessCollaboration " + models3.length);

		models3 = projectManager.getProject()
				.toModelElementArray(IModelElementFactory.MODEL_TYPE_ARCHI_MATE_BUSINESS_EVENT);
		System.out.print(models3.length == 0 ? "" : "\n" + "ArchiMateBusinessEvent " + models3.length);

		models3 = projectManager.getProject()
				.toModelElementArray(IModelElementFactory.MODEL_TYPE_ARCHI_MATE_BUSINESS_FUNCTION);
		System.out.print(models3.length == 0 ? "" : "\n" + "ArchiMateBusinessFunction " + models3.length);

		models3 = projectManager.getProject()
				.toModelElementArray(IModelElementFactory.MODEL_TYPE_ARCHI_MATE_BUSINESS_INTERACTION);
		System.out.print(models3.length == 0 ? "" : "\n" + "ArchiMateBusinessInteraction " + models3.length);

		models3 = projectManager.getProject()
				.toModelElementArray(IModelElementFactory.MODEL_TYPE_ARCHI_MATE_BUSINESS_INTERFACE);
		System.out.print(models3.length == 0 ? "" : "\n" + "ArchiMateBusinessInterface " + models3.length);

		models3 = projectManager.getProject()
				.toModelElementArray(IModelElementFactory.MODEL_TYPE_ARCHI_MATE_BUSINESS_OBJECT);
		System.out.print(models3.length == 0 ? "" : "\n" + "ArchiMateBusinessObject " + models3.length);

		models3 = projectManager.getProject()
				.toModelElementArray(IModelElementFactory.MODEL_TYPE_ARCHI_MATE_BUSINESS_PROCESS);
		System.out.print(models3.length == 0 ? "" : "\n" + "ArchiMateBusinessProcess " + models3.length);

		models3 = projectManager.getProject()
				.toModelElementArray(IModelElementFactory.MODEL_TYPE_ARCHI_MATE_BUSINESS_ROLE);
		System.out.print(models3.length == 0 ? "" : "\n" + "ArchiMateBusinessRole " + models3.length);

		models3 = projectManager.getProject()
				.toModelElementArray(IModelElementFactory.MODEL_TYPE_ARCHI_MATE_BUSINESS_SERVICE);
		System.out.print(models3.length == 0 ? "" : "\n" + "ArchiMateBusinessService " + models3.length);

		models3 = projectManager.getProject()
				.toModelElementArray(IModelElementFactory.MODEL_TYPE_ARCHI_MATE_COMMUNICATION_PATH);
		System.out.print(models3.length == 0 ? "" : "\n" + "ArchiMateCommunicationPath " + models3.length);

		models3 = projectManager.getProject()
				.toModelElementArray(IModelElementFactory.MODEL_TYPE_ARCHI_MATE_COMMUNICATION_PATH_BOX);
		System.out.print(models3.length == 0 ? "" : "\n" + "ArchiMateCommunicationPathBox " + models3.length);

		models3 = projectManager.getProject()
				.toModelElementArray(IModelElementFactory.MODEL_TYPE_ARCHI_MATE_COMPOSITION);
		System.out.print(models3.length == 0 ? "" : "\n" + "ArchiMateComposition " + models3.length);

		models3 = projectManager.getProject()
				.toModelElementArray(IModelElementFactory.MODEL_TYPE_ARCHI_MATE_CONSTRAINT);
		System.out.print(models3.length == 0 ? "" : "\n" + "ArchiMateConstraint " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_ARCHI_MATE_CONTRACT);
		System.out.print(models3.length == 0 ? "" : "\n" + "ArchiMateContract " + models3.length);

		models3 = projectManager.getProject()
				.toModelElementArray(IModelElementFactory.MODEL_TYPE_ARCHI_MATE_DATA_OBJECT);
		System.out.print(models3.length == 0 ? "" : "\n" + "ArchiMateDataObject " + models3.length);

		models3 = projectManager.getProject()
				.toModelElementArray(IModelElementFactory.MODEL_TYPE_ARCHI_MATE_DELIVERABLE);
		System.out.print(models3.length == 0 ? "" : "\n" + "ArchiMateDeliverable " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_ARCHI_MATE_DERIVED);
		System.out.print(models3.length == 0 ? "" : "\n" + "ArchiMateDerived " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_ARCHI_MATE_DEVICE);
		System.out.print(models3.length == 0 ? "" : "\n" + "ArchiMateDevice " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_ARCHI_MATE_DRIVER);
		System.out.print(models3.length == 0 ? "" : "\n" + "ArchiMateDriver " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_ARCHI_MATE_FLOW);
		System.out.print(models3.length == 0 ? "" : "\n" + "ArchiMateFlow " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_ARCHI_MATE_GAP);
		System.out.print(models3.length == 0 ? "" : "\n" + "ArchiMateGap " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_ARCHI_MATE_GOAL);
		System.out.print(models3.length == 0 ? "" : "\n" + "ArchiMateGoal " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_ARCHI_MATE_GROUPING);
		System.out.print(models3.length == 0 ? "" : "\n" + "ArchiMateGrouping " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_ARCHI_MATE_INFLUENCE);
		System.out.print(models3.length == 0 ? "" : "\n" + "ArchiMateInfluence " + models3.length);

		models3 = projectManager.getProject()
				.toModelElementArray(IModelElementFactory.MODEL_TYPE_ARCHI_MATE_INFRASTRUCTURE_FUNCTION);
		System.out.print(models3.length == 0 ? "" : "\n" + "ArchiMateInfrastructureFunction " + models3.length);

		models3 = projectManager.getProject()
				.toModelElementArray(IModelElementFactory.MODEL_TYPE_ARCHI_MATE_INFRASTRUCTURE_INTERFACE);
		System.out.print(models3.length == 0 ? "" : "\n" + "ArchiMateInfrastructureInterface " + models3.length);

		models3 = projectManager.getProject()
				.toModelElementArray(IModelElementFactory.MODEL_TYPE_ARCHI_MATE_INFRASTRUCTURE_SERVICE);
		System.out.print(models3.length == 0 ? "" : "\n" + "ArchiMateInfrastructureService " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_ARCHI_MATE_JUNCTION);
		System.out.print(models3.length == 0 ? "" : "\n" + "ArchiMateJunction " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_ARCHI_MATE_LOCATION);
		System.out.print(models3.length == 0 ? "" : "\n" + "ArchiMateLocation " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_ARCHI_MATE_MEANING);
		System.out.print(models3.length == 0 ? "" : "\n" + "ArchiMateMeaning " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_ARCHI_MATE_NETWORK);
		System.out.print(models3.length == 0 ? "" : "\n" + "ArchiMateNetwork " + models3.length);

		models3 = projectManager.getProject()
				.toModelElementArray(IModelElementFactory.MODEL_TYPE_ARCHI_MATE_NETWORK_BOX);
		System.out.print(models3.length == 0 ? "" : "\n" + "ArchiMateNetworkBox " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_ARCHI_MATE_NODE);
		System.out.print(models3.length == 0 ? "" : "\n" + "ArchiMateNode " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_ARCHI_MATE_PLATEAU);
		System.out.print(models3.length == 0 ? "" : "\n" + "ArchiMatePlateau " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_ARCHI_MATE_PRINCIPLE);
		System.out.print(models3.length == 0 ? "" : "\n" + "ArchiMatePrinciple " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_ARCHI_MATE_PRODUCT);
		System.out.print(models3.length == 0 ? "" : "\n" + "ArchiMateProduct " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_ARCHI_MATE_PROVIDE);
		System.out.print(models3.length == 0 ? "" : "\n" + "ArchiMateProvide " + models3.length);

		models3 = projectManager.getProject()
				.toModelElementArray(IModelElementFactory.MODEL_TYPE_ARCHI_MATE_REALIZATION);
		System.out.print(models3.length == 0 ? "" : "\n" + "ArchiMateRealization " + models3.length);

		models3 = projectManager.getProject()
				.toModelElementArray(IModelElementFactory.MODEL_TYPE_ARCHI_MATE_REPRESENTATION);
		System.out.print(models3.length == 0 ? "" : "\n" + "ArchiMateRepresentation " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_ARCHI_MATE_REQUIRE);
		System.out.print(models3.length == 0 ? "" : "\n" + "ArchiMateRequire " + models3.length);

		models3 = projectManager.getProject()
				.toModelElementArray(IModelElementFactory.MODEL_TYPE_ARCHI_MATE_REQUIREMENT);
		System.out.print(models3.length == 0 ? "" : "\n" + "ArchiMateRequirement " + models3.length);

		models3 = projectManager.getProject()
				.toModelElementArray(IModelElementFactory.MODEL_TYPE_ARCHI_MATE_SPECIALIZATION);
		System.out.print(models3.length == 0 ? "" : "\n" + "ArchiMateSpecialization " + models3.length);

		models3 = projectManager.getProject()
				.toModelElementArray(IModelElementFactory.MODEL_TYPE_ARCHI_MATE_STAKEHOLDER);
		System.out.print(models3.length == 0 ? "" : "\n" + "ArchiMateStakeholder " + models3.length);

		models3 = projectManager.getProject()
				.toModelElementArray(IModelElementFactory.MODEL_TYPE_ARCHI_MATE_SYSTEM_SOFTWARE);
		System.out.print(models3.length == 0 ? "" : "\n" + "ArchiMateSystemSoftware " + models3.length);

		models3 = projectManager.getProject()
				.toModelElementArray(IModelElementFactory.MODEL_TYPE_ARCHI_MATE_TRIGGERING);
		System.out.print(models3.length == 0 ? "" : "\n" + "ArchiMateTriggering " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_ARCHI_MATE_USED_BY);
		System.out.print(models3.length == 0 ? "" : "\n" + "ArchiMateUsedBy " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_ARCHI_MATE_VALUE);
		System.out.print(models3.length == 0 ? "" : "\n" + "ArchiMateValue " + models3.length);

		models3 = projectManager.getProject()
				.toModelElementArray(IModelElementFactory.MODEL_TYPE_ARCHI_MATE_WORK_PACKAGE);
		System.out.print(models3.length == 0 ? "" : "\n" + "ArchiMateWorkPackage " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_ARTIFACT);
		System.out.print(models3.length == 0 ? "" : "\n" + "Artifact " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_ASSOCIATION);
		System.out.print(models3.length == 0 ? "" : "\n" + "Association " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_ASSOCIATION_CLASS);
		System.out.print(models3.length == 0 ? "" : "\n" + "AssociationClass " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_ASSOCIATION_END);
		System.out.print(models3.length == 0 ? "" : "\n" + "AssociationEnd " + models3.length);

		models3 = projectManager.getProject()
				.toModelElementArray(IModelElementFactory.MODEL_TYPE_ASSOCIATION_END_PROPERTY_STRINGS);
		System.out.print(models3.length == 0 ? "" : "\n" + "AssociationEndPropertyStrings " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_ATTRIBUTE);
		System.out.print(models3.length == 0 ? "" : "\n" + "Attribute " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_ATTRIBUTE_LINK);
		System.out.print(models3.length == 0 ? "" : "\n" + "AttributeLink " + models3.length);

		models3 = projectManager.getProject()
				.toModelElementArray(IModelElementFactory.MODEL_TYPE_AUTO_ATTRIBUTE_TYPE_MODEL);
		System.out.print(models3.length == 0 ? "" : "\n" + "AutoAttributeTypeModel " + models3.length);

		models3 = projectManager.getProject()
				.toModelElementArray(IModelElementFactory.MODEL_TYPE_AUTO_ATTRIBUTE_TYPE_MODEL_CONTAINER);
		System.out.print(models3.length == 0 ? "" : "\n" + "AutoAttributeTypeModelContainer " + models3.length);

		models3 = projectManager.getProject()
				.toModelElementArray(IModelElementFactory.MODEL_TYPE_AUTO_COLUMN_TYPE_MODEL);
		System.out.print(models3.length == 0 ? "" : "\n" + "AutoColumnTypeModel " + models3.length);

		models3 = projectManager.getProject()
				.toModelElementArray(IModelElementFactory.MODEL_TYPE_AUTO_COLUMN_TYPE_MODEL_CONTAINER);
		System.out.print(models3.length == 0 ? "" : "\n" + "AutoColumnTypeModelContainer " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_BINDING_DEPENDENCY);
		System.out.print(models3.length == 0 ? "" : "\n" + "BindingDependency " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_BMM_ASSESSMENT);
		System.out.print(models3.length == 0 ? "" : "\n" + "BMMAssessment " + models3.length);

		models3 = projectManager.getProject()
				.toModelElementArray(IModelElementFactory.MODEL_TYPE_BMM_ASSESSMENT_CATEGORY);
		System.out.print(models3.length == 0 ? "" : "\n" + "BMMAssessmentCategory " + models3.length);

		models3 = projectManager.getProject()
				.toModelElementArray(IModelElementFactory.MODEL_TYPE_BMM_ASSESSMENT_CATEGORY_CONTAINER);
		System.out.print(models3.length == 0 ? "" : "\n" + "BMMAssessmentCategoryContainer " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_BMM_ASSET);
		System.out.print(models3.length == 0 ? "" : "\n" + "BMMAsset " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_BMM_ASSET_CATEGORY);
		System.out.print(models3.length == 0 ? "" : "\n" + "BMMAssetCategory " + models3.length);

		models3 = projectManager.getProject()
				.toModelElementArray(IModelElementFactory.MODEL_TYPE_BMM_ASSET_CATEGORY_CONTAINER);
		System.out.print(models3.length == 0 ? "" : "\n" + "BMMAssetCategoryContainer " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_BMM_BUSINESS_POLICY);
		System.out.print(models3.length == 0 ? "" : "\n" + "BMMBusinessPolicy " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_BMM_BUSINESS_PROCESS);
		System.out.print(models3.length == 0 ? "" : "\n" + "BMMBusinessProcess " + models3.length);

		models3 = projectManager.getProject()
				.toModelElementArray(IModelElementFactory.MODEL_TYPE_BMM_BUSINESS_RULE_ENFORCEMENT_LEVEL);
		System.out.print(models3.length == 0 ? "" : "\n" + "BMMBusinessRuleEnforcementLevel " + models3.length);

		models3 = projectManager.getProject()
				.toModelElementArray(IModelElementFactory.MODEL_TYPE_BMM_BUSINESS_RULE_ENFORCEMENT_LEVEL_CONTAINER);
		System.out
				.print(models3.length == 0 ? "" : "\n" + "BMMBusinessRuleEnforcementLevelContainer " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_BMM_COMPOSITION);
		System.out.print(models3.length == 0 ? "" : "\n" + "BMMComposition " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_BMM_END);
		System.out.print(models3.length == 0 ? "" : "\n" + "BMMEnd " + models3.length);

		models3 = projectManager.getProject()
				.toModelElementArray(IModelElementFactory.MODEL_TYPE_BMM_EXTERNAL_INFLUENCER);
		System.out.print(models3.length == 0 ? "" : "\n" + "BMMExternalInfluencer " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_BMM_GOAL);
		System.out.print(models3.length == 0 ? "" : "\n" + "BMMGoal " + models3.length);

		models3 = projectManager.getProject()
				.toModelElementArray(IModelElementFactory.MODEL_TYPE_BMM_INFLUENCER_CATEGORY);
		System.out.print(models3.length == 0 ? "" : "\n" + "BMMInfluencerCategory " + models3.length);

		models3 = projectManager.getProject()
				.toModelElementArray(IModelElementFactory.MODEL_TYPE_BMM_INFLUENCER_CATEGORY_CONTAINER);
		System.out.print(models3.length == 0 ? "" : "\n" + "BMMInfluencerCategoryContainer " + models3.length);

		models3 = projectManager.getProject()
				.toModelElementArray(IModelElementFactory.MODEL_TYPE_BMM_INFLUENCING_ORGANIZATION);
		System.out.print(models3.length == 0 ? "" : "\n" + "BMMInfluencingOrganization " + models3.length);

		models3 = projectManager.getProject()
				.toModelElementArray(IModelElementFactory.MODEL_TYPE_BMM_INFLUENCING_ORGANIZATION_CATEGORY);
		System.out.print(models3.length == 0 ? "" : "\n" + "BMMInfluencingOrganizationCategory " + models3.length);

		models3 = projectManager.getProject()
				.toModelElementArray(IModelElementFactory.MODEL_TYPE_BMM_INFLUENCING_ORGANIZATION_CATEGORY_CONTAINER);
		System.out.print(
				models3.length == 0 ? "" : "\n" + "BMMInfluencingOrganizationCategoryContainer " + models3.length);

		models3 = projectManager.getProject()
				.toModelElementArray(IModelElementFactory.MODEL_TYPE_BMM_INTERNAL_INFLUENCER);
		System.out.print(models3.length == 0 ? "" : "\n" + "BMMInternalInfluencer " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_BMM_LIABILITY);
		System.out.print(models3.length == 0 ? "" : "\n" + "BMMLiability " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_BMM_MEANS);
		System.out.print(models3.length == 0 ? "" : "\n" + "BMMMeans " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_BMM_MISSION);
		System.out.print(models3.length == 0 ? "" : "\n" + "BMMMission " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_BMM_OBJECTIVE);
		System.out.print(models3.length == 0 ? "" : "\n" + "BMMObjective " + models3.length);

		models3 = projectManager.getProject()
				.toModelElementArray(IModelElementFactory.MODEL_TYPE_BMM_ORGANIZATION_UNIT);
		System.out.print(models3.length == 0 ? "" : "\n" + "BMMOrganizationUnit " + models3.length);

		models3 = projectManager.getProject()
				.toModelElementArray(IModelElementFactory.MODEL_TYPE_BMM_ORGANIZATION_UNIT_CATEGORY);
		System.out.print(models3.length == 0 ? "" : "\n" + "BMMOrganizationUnitCategory " + models3.length);

		models3 = projectManager.getProject()
				.toModelElementArray(IModelElementFactory.MODEL_TYPE_BMM_ORGANIZATION_UNIT_CATEGORY_CONTAINER);
		System.out.print(models3.length == 0 ? "" : "\n" + "BMMOrganizationUnitCategoryContainer " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_BMM_POTENTIAL_REWARD);
		System.out.print(models3.length == 0 ? "" : "\n" + "BMMPotentialReward " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_BMM_RELATIONSHIP);
		System.out.print(models3.length == 0 ? "" : "\n" + "BMMRelationship " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_BMM_RISK);
		System.out.print(models3.length == 0 ? "" : "\n" + "BMMRisk " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_BMM_STRATEGY);
		System.out.print(models3.length == 0 ? "" : "\n" + "BMMStrategy " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_BMM_TACTIC);
		System.out.print(models3.length == 0 ? "" : "\n" + "BMMTactic " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_BMM_VISION);
		System.out.print(models3.length == 0 ? "" : "\n" + "BMMVision " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_BOOKMARK);
		System.out.print(models3.length == 0 ? "" : "\n" + "Bookmark " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_BOOKMARK_FOLDER);
		System.out.print(models3.length == 0 ? "" : "\n" + "BookmarkFolder " + models3.length);

		models3 = projectManager.getProject()
				.toModelElementArray(IModelElementFactory.MODEL_TYPE_BPD_SIMULACIAN_DETAILS);
		System.out.print(models3.length == 0 ? "" : "\n" + "BPDSimulacianDetails " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_BPELXML);
		System.out.print(models3.length == 0 ? "" : "\n" + "BPELXML " + models3.length);

		models3 = projectManager.getProject()
				.toModelElementArray(IModelElementFactory.MODEL_TYPE_BPEL_ASSIGNMENT_FROM_EXPRESSION);
		System.out.print(models3.length == 0 ? "" : "\n" + "BPELAssignmentFromExpression " + models3.length);

		models3 = projectManager.getProject()
				.toModelElementArray(IModelElementFactory.MODEL_TYPE_BPEL_ASSIGNMENT_FROM_PARTNER_LINK);
		System.out.print(models3.length == 0 ? "" : "\n" + "BPELAssignmentFromPartnerLink " + models3.length);

		models3 = projectManager.getProject()
				.toModelElementArray(IModelElementFactory.MODEL_TYPE_BPEL_ASSIGNMENT_FROM_PROPERTY);
		System.out.print(models3.length == 0 ? "" : "\n" + "BPELAssignmentFromProperty " + models3.length);

		models3 = projectManager.getProject()
				.toModelElementArray(IModelElementFactory.MODEL_TYPE_BPEL_ASSIGNMENT_FROM_VARIABLE);
		System.out.print(models3.length == 0 ? "" : "\n" + "BPELAssignmentFromVariable " + models3.length);

		models3 = projectManager.getProject()
				.toModelElementArray(IModelElementFactory.MODEL_TYPE_BPEL_ASSIGNMENT_TO_PARTNER_LINK);
		System.out.print(models3.length == 0 ? "" : "\n" + "BPELAssignmentToPartnerLink " + models3.length);

		models3 = projectManager.getProject()
				.toModelElementArray(IModelElementFactory.MODEL_TYPE_BPEL_ASSIGNMENT_TO_PROPERTY);
		System.out.print(models3.length == 0 ? "" : "\n" + "BPELAssignmentToProperty " + models3.length);

		models3 = projectManager.getProject()
				.toModelElementArray(IModelElementFactory.MODEL_TYPE_BPEL_ASSIGNMENT_TO_VARIABLE);
		System.out.print(models3.length == 0 ? "" : "\n" + "BPELAssignmentToVariable " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_BPEL_CORRELATION);
		System.out.print(models3.length == 0 ? "" : "\n" + "BPELCorrelation " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_BPEL_CORRELATION_SET);
		System.out.print(models3.length == 0 ? "" : "\n" + "BPELCorrelationSet " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_BPEL_ELEMENT_PART);
		System.out.print(models3.length == 0 ? "" : "\n" + "BPELElementPart " + models3.length);

		models3 = projectManager.getProject()
				.toModelElementArray(IModelElementFactory.MODEL_TYPE_BPEL_ELEMENT_PROPERTY);
		System.out.print(models3.length == 0 ? "" : "\n" + "BPELElementProperty " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_BPEL_NAMESPACE_MAP);
		System.out.print(models3.length == 0 ? "" : "\n" + "BPELNamespaceMap " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_BPEL_OPERATION);
		System.out.print(models3.length == 0 ? "" : "\n" + "BPELOperation " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_BPEL_PART);
		System.out.print(models3.length == 0 ? "" : "\n" + "BPELPart " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_BPEL_PARTNER_LINK);
		System.out.print(models3.length == 0 ? "" : "\n" + "BPELPartnerLink " + models3.length);

		models3 = projectManager.getProject()
				.toModelElementArray(IModelElementFactory.MODEL_TYPE_BPEL_PARTNER_LINK_TYPE);
		System.out.print(models3.length == 0 ? "" : "\n" + "BPELPartnerLinkType " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_BPEL_PORT_TYPE);
		System.out.print(models3.length == 0 ? "" : "\n" + "BPELPortType " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_BPEL_PROPERTY_ALIAS);
		System.out.print(models3.length == 0 ? "" : "\n" + "BPELPropertyAlias " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_BPEL_SCHEMA);
		System.out.print(models3.length == 0 ? "" : "\n" + "BPELSchema " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_BPEL_TYPE_PART);
		System.out.print(models3.length == 0 ? "" : "\n" + "BPELTypePart " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_BPEL_TYPE_PROPERTY);
		System.out.print(models3.length == 0 ? "" : "\n" + "BPELTypeProperty " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_BPEL_VARIABLE);
		System.out.print(models3.length == 0 ? "" : "\n" + "BPELVariable " + models3.length);

		models3 = projectManager.getProject()
				.toModelElementArray(IModelElementFactory.MODEL_TYPE_BPMI_FLOW_CONDITION_ALL);
		System.out.print(models3.length == 0 ? "" : "\n" + "BPMIFlowConditionAll " + models3.length);

		models3 = projectManager.getProject()
				.toModelElementArray(IModelElementFactory.MODEL_TYPE_BPMI_FLOW_CONDITION_COMPLEX);
		System.out.print(models3.length == 0 ? "" : "\n" + "BPMIFlowConditionComplex " + models3.length);

		models3 = projectManager.getProject()
				.toModelElementArray(IModelElementFactory.MODEL_TYPE_BPMI_FLOW_CONDITION_ONE);
		System.out.print(models3.length == 0 ? "" : "\n" + "BPMIFlowConditionOne " + models3.length);

		models3 = projectManager.getProject()
				.toModelElementArray(IModelElementFactory.MODEL_TYPE_BPMI_ORDERING_PARALLEL);
		System.out.print(models3.length == 0 ? "" : "\n" + "BPMIOrderingParallel " + models3.length);

		models3 = projectManager.getProject()
				.toModelElementArray(IModelElementFactory.MODEL_TYPE_BPMI_ORDERING_SEQUENTIAL);
		System.out.print(models3.length == 0 ? "" : "\n" + "BPMIOrderingSequential " + models3.length);

		models3 = projectManager.getProject()
				.toModelElementArray(IModelElementFactory.MODEL_TYPE_BPWS_PARTNER_LINK_TYPE);
		System.out.print(models3.length == 0 ? "" : "\n" + "BPWSPartnerLinkType " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_BPWS_PROPERTY);
		System.out.print(models3.length == 0 ? "" : "\n" + "BPWSProperty " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_BPWS_PROPERTY_ALIAS);
		System.out.print(models3.length == 0 ? "" : "\n" + "BPWSPropertyAlias " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_BPWS_QUERY);
		System.out.print(models3.length == 0 ? "" : "\n" + "BPWSQuery " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_BPWS_ROLE);
		System.out.print(models3.length == 0 ? "" : "\n" + "BPWSRole " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_BP_ACTIVITY_RESOURCE);
		System.out.print(models3.length == 0 ? "" : "\n" + "BPActivityResource " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_BP_ASSIGNMENT);
		System.out.print(models3.length == 0 ? "" : "\n" + "BPAssignment " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_BP_ASSOCIATION);
		System.out.print(models3.length == 0 ? "" : "\n" + "BPAssociation " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_BP_AUDITING);
		System.out.print(models3.length == 0 ? "" : "\n" + "BPAuditing " + models3.length);

		models3 = projectManager.getProject()
				.toModelElementArray(IModelElementFactory.MODEL_TYPE_BP_BUSINESS_RULE_TASK);
		System.out.print(models3.length == 0 ? "" : "\n" + "BPBusinessRuleTask " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_BP_CALL_ACTIVITY);
		System.out.print(models3.length == 0 ? "" : "\n" + "BPCallActivity " + models3.length);

		models3 = projectManager.getProject()
				.toModelElementArray(IModelElementFactory.MODEL_TYPE_BP_CALL_CHOREOGRAPHY_ACTIVITY);
		System.out.print(models3.length == 0 ? "" : "\n" + "BPCallChoreographyActivity " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_BP_CANCEL_RESULT);
		System.out.print(models3.length == 0 ? "" : "\n" + "BPCancelResult " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_BP_CANCEL_TRIGGER);
		System.out.print(models3.length == 0 ? "" : "\n" + "BPCancelTrigger " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_BP_CATEGORY);
		System.out.print(models3.length == 0 ? "" : "\n" + "BPCategory " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_BP_CATEGORY_VALUE);
		System.out.print(models3.length == 0 ? "" : "\n" + "BPCategoryValue " + models3.length);

		models3 = projectManager.getProject()
				.toModelElementArray(IModelElementFactory.MODEL_TYPE_BP_CHOREOGRAPHY_SUB_PROCESS);
		System.out.print(models3.length == 0 ? "" : "\n" + "BPChoreographySubProcess " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_BP_CHOREOGRAPHY_TASK);
		System.out.print(models3.length == 0 ? "" : "\n" + "BPChoreographyTask " + models3.length);

		models3 = projectManager.getProject()
				.toModelElementArray(IModelElementFactory.MODEL_TYPE_BP_COMPENSATION_RESULT);
		System.out.print(models3.length == 0 ? "" : "\n" + "BPCompensationResult " + models3.length);

		models3 = projectManager.getProject()
				.toModelElementArray(IModelElementFactory.MODEL_TYPE_BP_COMPENSATION_TRIGGER);
		System.out.print(models3.length == 0 ? "" : "\n" + "BPCompensationTrigger " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_BP_CORRELATION_KEY);
		System.out.print(models3.length == 0 ? "" : "\n" + "BPCorrelationKey " + models3.length);

		models3 = projectManager.getProject()
				.toModelElementArray(IModelElementFactory.MODEL_TYPE_BP_CORRELATION_PROPERTY);
		System.out.print(models3.length == 0 ? "" : "\n" + "BPCorrelationProperty " + models3.length);

		models3 = projectManager.getProject()
				.toModelElementArray(IModelElementFactory.MODEL_TYPE_BP_CORRELATION_PROPERTY_RETRIEVAL_EXPRESSION);
		System.out
				.print(models3.length == 0 ? "" : "\n" + "BPCorrelationPropertyRetrievalExpression " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_BP_DATA_ASSOCIATION);
		System.out.print(models3.length == 0 ? "" : "\n" + "BPDataAssociation " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_BP_DATA_INPUT);
		System.out.print(models3.length == 0 ? "" : "\n" + "BPDataInput " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_BP_DATA_OBJECT);
		System.out.print(models3.length == 0 ? "" : "\n" + "BPDataObject " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_BP_DATA_OBJECT_STATE);
		System.out.print(models3.length == 0 ? "" : "\n" + "BPDataObjectState " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_BP_DATA_OUTPUT);
		System.out.print(models3.length == 0 ? "" : "\n" + "BPDataOutput " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_BP_DATA_STORE);
		System.out.print(models3.length == 0 ? "" : "\n" + "BPDataStore " + models3.length);

		models3 = projectManager.getProject()
				.toModelElementArray(IModelElementFactory.MODEL_TYPE_BP_EMBEDDED_SUB_PROCESS);
		System.out.print(models3.length == 0 ? "" : "\n" + "BPEmbeddedSubProcess " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_BP_END_EVENT);
		System.out.print(models3.length == 0 ? "" : "\n" + "BPEndEvent " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_BP_ENTITY);
		System.out.print(models3.length == 0 ? "" : "\n" + "BPEntity " + models3.length);

		models3 = projectManager.getProject()
				.toModelElementArray(IModelElementFactory.MODEL_TYPE_BP_ENTITY_PARTICIPANT);
		System.out.print(models3.length == 0 ? "" : "\n" + "BPEntityParticipant " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_BP_ERROR);
		System.out.print(models3.length == 0 ? "" : "\n" + "BPError " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_BP_ERROR_RESULT);
		System.out.print(models3.length == 0 ? "" : "\n" + "BPErrorResult " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_BP_ERROR_TRIGGER);
		System.out.print(models3.length == 0 ? "" : "\n" + "BPErrorTrigger " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_BP_ESCALATION_RESULT);
		System.out.print(models3.length == 0 ? "" : "\n" + "BPEscalationResult " + models3.length);

		models3 = projectManager.getProject()
				.toModelElementArray(IModelElementFactory.MODEL_TYPE_BP_ESCALATION_TRIGGER);
		System.out.print(models3.length == 0 ? "" : "\n" + "BPEscalationTrigger " + models3.length);

		models3 = projectManager.getProject()
				.toModelElementArray(IModelElementFactory.MODEL_TYPE_BP_FLOW_OBJECT_SIMULACIAN_MODEL);
		System.out.print(models3.length == 0 ? "" : "\n" + "BPFlowObjectSimulacianModel " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_BP_FORMAL_EXPRESSION);
		System.out.print(models3.length == 0 ? "" : "\n" + "BPFormalExpression " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_BP_GATE);
		System.out.print(models3.length == 0 ? "" : "\n" + "BPGate " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_BP_GATEWAY);
		System.out.print(models3.length == 0 ? "" : "\n" + "BPGateway " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_BP_GATEWAY_AND);
		System.out.print(models3.length == 0 ? "" : "\n" + "BPGatewayAND " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_BP_GATEWAY_COMPLEX);
		System.out.print(models3.length == 0 ? "" : "\n" + "BPGatewayComplex " + models3.length);

		models3 = projectManager.getProject()
				.toModelElementArray(IModelElementFactory.MODEL_TYPE_BP_GATEWAY_DATA_BASED_XOR);
		System.out.print(models3.length == 0 ? "" : "\n" + "BPGatewayDataBasedXOR " + models3.length);

		models3 = projectManager.getProject()
				.toModelElementArray(IModelElementFactory.MODEL_TYPE_BP_GATEWAY_EVENT_BASED_XOR);
		System.out.print(models3.length == 0 ? "" : "\n" + "BPGatewayEventBasedXOR " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_BP_GATEWAY_OR);
		System.out.print(models3.length == 0 ? "" : "\n" + "BPGatewayOR " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_BP_GROUP);
		System.out.print(models3.length == 0 ? "" : "\n" + "BPGroup " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_BP_HUMAN_PERFORMER);
		System.out.print(models3.length == 0 ? "" : "\n" + "BPHumanPerformer " + models3.length);

		models3 = projectManager.getProject()
				.toModelElementArray(IModelElementFactory.MODEL_TYPE_BP_IMPL_BUSINESS_RULE);
		System.out.print(models3.length == 0 ? "" : "\n" + "BPImplBusinessRule " + models3.length);

		models3 = projectManager.getProject()
				.toModelElementArray(IModelElementFactory.MODEL_TYPE_BP_IMPL_HUMAN_TASK_WEB_SERVICE);
		System.out.print(models3.length == 0 ? "" : "\n" + "BPImplHumanTaskWebService " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_BP_IMPL_OTHER);
		System.out.print(models3.length == 0 ? "" : "\n" + "BPImplOther " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_BP_IMPL_WEB_SERVICE);
		System.out.print(models3.length == 0 ? "" : "\n" + "BPImplWebService " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_BP_IMPORT);
		System.out.print(models3.length == 0 ? "" : "\n" + "BPImport " + models3.length);

		models3 = projectManager.getProject()
				.toModelElementArray(IModelElementFactory.MODEL_TYPE_BP_INDEPENDENT_SUB_PROCESS);
		System.out.print(models3.length == 0 ? "" : "\n" + "BPIndependentSubProcess " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_BP_INPUT);
		System.out.print(models3.length == 0 ? "" : "\n" + "BPInput " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_BP_INPUT_ELEMENT);
		System.out.print(models3.length == 0 ? "" : "\n" + "BPInputElement " + models3.length);

		models3 = projectManager.getProject()
				.toModelElementArray(IModelElementFactory.MODEL_TYPE_BP_INTERMEDIATE_EVENT);
		System.out.print(models3.length == 0 ? "" : "\n" + "BPIntermediateEvent " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_BP_ITEM_DEFINITION);
		System.out.print(models3.length == 0 ? "" : "\n" + "BPItemDefinition " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_BP_LANE);
		System.out.print(models3.length == 0 ? "" : "\n" + "BPLane " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_BP_LINK_RESULT);
		System.out.print(models3.length == 0 ? "" : "\n" + "BPLinkResult " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_BP_LINK_TRIGGER);
		System.out.print(models3.length == 0 ? "" : "\n" + "BPLinkTrigger " + models3.length);

		models3 = projectManager.getProject()
				.toModelElementArray(IModelElementFactory.MODEL_TYPE_BP_LOOP_MULTI_INSTANCE);
		System.out.print(models3.length == 0 ? "" : "\n" + "BPLoopMultiInstance " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_BP_LOOP_STANDARD);
		System.out.print(models3.length == 0 ? "" : "\n" + "BPLoopStandard " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_BP_MANUAL_TASK);
		System.out.print(models3.length == 0 ? "" : "\n" + "BPManualTask " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_BP_MESSAGE);
		System.out.print(models3.length == 0 ? "" : "\n" + "BPMessage " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_BP_MESSAGE_FLOW);
		System.out.print(models3.length == 0 ? "" : "\n" + "BPMessageFlow " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_BP_MESSAGE_RESULT);
		System.out.print(models3.length == 0 ? "" : "\n" + "BPMessageResult " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_BP_MESSAGE_TRIGGER);
		System.out.print(models3.length == 0 ? "" : "\n" + "BPMessageTrigger " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_BP_MONITORING);
		System.out.print(models3.length == 0 ? "" : "\n" + "BPMonitoring " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_BP_MULTIPLE_RESULT);
		System.out.print(models3.length == 0 ? "" : "\n" + "BPMultipleResult " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_BP_MULTIPLE_TRIGGER);
		System.out.print(models3.length == 0 ? "" : "\n" + "BPMultipleTrigger " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_BP_OUTPUT);
		System.out.print(models3.length == 0 ? "" : "\n" + "BPOutput " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_BP_OUTPUT_ELEMENT);
		System.out.print(models3.length == 0 ? "" : "\n" + "BPOutputElement " + models3.length);

		models3 = projectManager.getProject()
				.toModelElementArray(IModelElementFactory.MODEL_TYPE_BP_PARALLEL_MULTIPLE_TRIGGER);
		System.out.print(models3.length == 0 ? "" : "\n" + "BPParallelMultipleTrigger " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_BP_PARTICIPANT);
		System.out.print(models3.length == 0 ? "" : "\n" + "BPParticipant " + models3.length);

		models3 = projectManager.getProject()
				.toModelElementArray(IModelElementFactory.MODEL_TYPE_BP_PARTICIPANT_ASSOCIATION);
		System.out.print(models3.length == 0 ? "" : "\n" + "BPParticipantAssociation " + models3.length);

		models3 = projectManager.getProject()
				.toModelElementArray(IModelElementFactory.MODEL_TYPE_BP_PARTICIPANT_MULTIPLICITY);
		System.out.print(models3.length == 0 ? "" : "\n" + "BPParticipantMultiplicity " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_BP_PERFORMER);
		System.out.print(models3.length == 0 ? "" : "\n" + "BPPerformer " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_BP_POOL);
		System.out.print(models3.length == 0 ? "" : "\n" + "BPPool " + models3.length);

		models3 = projectManager.getProject()
				.toModelElementArray(IModelElementFactory.MODEL_TYPE_BP_POOL_LANE_SIMULACIAN_MODEL);
		System.out.print(models3.length == 0 ? "" : "\n" + "BPPoolLaneSimulacianModel " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_BP_POTENTIAL_OWNER);
		System.out.print(models3.length == 0 ? "" : "\n" + "BPPotentialOwner " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_BP_PROCEDURE);
		System.out.print(models3.length == 0 ? "" : "\n" + "BPProcedure " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_BP_PROCEDURE_STEP);
		System.out.print(models3.length == 0 ? "" : "\n" + "BPProcedureStep " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_BP_PROCESS);
		System.out.print(models3.length == 0 ? "" : "\n" + "BPProcess " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_BP_PROPERTY);
		System.out.print(models3.length == 0 ? "" : "\n" + "BPProperty " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_BP_RECEIVE_TASK);
		System.out.print(models3.length == 0 ? "" : "\n" + "BPReceiveTask " + models3.length);

		models3 = projectManager.getProject()
				.toModelElementArray(IModelElementFactory.MODEL_TYPE_BP_REFERENCE_SUB_PROCESS);
		System.out.print(models3.length == 0 ? "" : "\n" + "BPReferenceSubProcess " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_BP_REFERENCE_TASK);
		System.out.print(models3.length == 0 ? "" : "\n" + "BPReferenceTask " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_BP_RESOURCE);
		System.out.print(models3.length == 0 ? "" : "\n" + "BPResource " + models3.length);

		models3 = projectManager.getProject()
				.toModelElementArray(IModelElementFactory.MODEL_TYPE_BP_RESOURCE_ASSIGNMENT_EXPRESSION);
		System.out.print(models3.length == 0 ? "" : "\n" + "BPResourceAssignmentExpression " + models3.length);

		models3 = projectManager.getProject()
				.toModelElementArray(IModelElementFactory.MODEL_TYPE_BP_RESOURCE_PARAMETER);
		System.out.print(models3.length == 0 ? "" : "\n" + "BPResourceParameter " + models3.length);

		models3 = projectManager.getProject()
				.toModelElementArray(IModelElementFactory.MODEL_TYPE_BP_RESOURCE_PARAMETER_BINDING);
		System.out.print(models3.length == 0 ? "" : "\n" + "BPResourceParameterBinding " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_BP_ROLE);
		System.out.print(models3.length == 0 ? "" : "\n" + "BPRole " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_BP_ROLE_PARTICIPANT);
		System.out.print(models3.length == 0 ? "" : "\n" + "BPRoleParticipant " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_BP_RULE);
		System.out.print(models3.length == 0 ? "" : "\n" + "BPRule " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_BP_RULE_TRIGGER);
		System.out.print(models3.length == 0 ? "" : "\n" + "BPRuleTrigger " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_BP_SCRIPT_TASK);
		System.out.print(models3.length == 0 ? "" : "\n" + "BPScriptTask " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_BP_SEND_TASK);
		System.out.print(models3.length == 0 ? "" : "\n" + "BPSendTask " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_BP_SEQUENCE_FLOW);
		System.out.print(models3.length == 0 ? "" : "\n" + "BPSequenceFlow " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_BP_SERVICE_TASK);
		System.out.print(models3.length == 0 ? "" : "\n" + "BPServiceTask " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_BP_SIGNAL);
		System.out.print(models3.length == 0 ? "" : "\n" + "BPSignal " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_BP_SIGNAL_RESULT);
		System.out.print(models3.length == 0 ? "" : "\n" + "BPSignalResult " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_BP_SIGNAL_TRIGGER);
		System.out.print(models3.length == 0 ? "" : "\n" + "BPSignalTrigger " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_BP_SIM_CONTAINER);
		System.out.print(models3.length == 0 ? "" : "\n" + "BPSimContainer " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_BP_SIM_DECISION);
		System.out.print(models3.length == 0 ? "" : "\n" + "BPSimDecision " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_BP_SIM_INPUT);
		System.out.print(models3.length == 0 ? "" : "\n" + "BPSimInput " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_BP_SIM_INPUT_GROUP);
		System.out.print(models3.length == 0 ? "" : "\n" + "BPSimInputGroup " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_BP_SIM_INPUT_PROFILE);
		System.out.print(models3.length == 0 ? "" : "\n" + "BPSimInputProfile " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_BP_SIM_PROFILE);
		System.out.print(models3.length == 0 ? "" : "\n" + "BPSimProfile " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_BP_SIM_PROFILE_GROUP);
		System.out.print(models3.length == 0 ? "" : "\n" + "BPSimProfileGroup " + models3.length);

		models3 = projectManager.getProject()
				.toModelElementArray(IModelElementFactory.MODEL_TYPE_BP_SIM_REQUIRED_RESOURCE);
		System.out.print(models3.length == 0 ? "" : "\n" + "BPSimRequiredResource " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_BP_SIM_RESOURCE);
		System.out.print(models3.length == 0 ? "" : "\n" + "BPSimResource " + models3.length);

		models3 = projectManager.getProject()
				.toModelElementArray(IModelElementFactory.MODEL_TYPE_BP_SIM_RESOURCE_GROUP);
		System.out.print(models3.length == 0 ? "" : "\n" + "BPSimResourceGroup " + models3.length);

		models3 = projectManager.getProject()
				.toModelElementArray(IModelElementFactory.MODEL_TYPE_BP_SIM_RESOURCE_PROFILE);
		System.out.print(models3.length == 0 ? "" : "\n" + "BPSimResourceProfile " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_BP_START_EVENT);
		System.out.print(models3.length == 0 ? "" : "\n" + "BPStartEvent " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_BP_SUB_PROCESS);
		System.out.print(models3.length == 0 ? "" : "\n" + "BPSubProcess " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_BP_TASK);
		System.out.print(models3.length == 0 ? "" : "\n" + "BPTask " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_BP_TERMINATE_RESULT);
		System.out.print(models3.length == 0 ? "" : "\n" + "BPTerminateResult " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_BP_TEXT_ANNOTATION);
		System.out.print(models3.length == 0 ? "" : "\n" + "BPTextAnnotation " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_BP_TIMER_TRIGGER);
		System.out.print(models3.length == 0 ? "" : "\n" + "BPTimerTrigger " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_BP_TRANSACTION);
		System.out.print(models3.length == 0 ? "" : "\n" + "BPTransaction " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_BP_USER_TASK);
		System.out.print(models3.length == 0 ? "" : "\n" + "BPUserTask " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_BRAINSTORM_NOTE);
		System.out.print(models3.length == 0 ? "" : "\n" + "BrainstormNote " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_BRAINSTORM_NOTE_TAG);
		System.out.print(models3.length == 0 ? "" : "\n" + "BrainstormNoteTag " + models3.length);

		models3 = projectManager.getProject()
				.toModelElementArray(IModelElementFactory.MODEL_TYPE_BRAINSTORM_NOTE_TAG_CONTAINER);
		System.out.print(models3.length == 0 ? "" : "\n" + "BrainstormNoteTagContainer " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_BR_KEYWORD);
		System.out.print(models3.length == 0 ? "" : "\n" + "BRKeyword " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_BR_KEYWORDS);
		System.out.print(models3.length == 0 ? "" : "\n" + "BRKeywords " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_BUSINESS_RULE);
		System.out.print(models3.length == 0 ? "" : "\n" + "BusinessRule " + models3.length);

		models3 = projectManager.getProject()
				.toModelElementArray(IModelElementFactory.MODEL_TYPE_BUSINESS_RULE_ASSOCIATION);
		System.out.print(models3.length == 0 ? "" : "\n" + "BusinessRuleAssociation " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_BUSINESS_RULE_GROUP);
		System.out.print(models3.length == 0 ? "" : "\n" + "BusinessRuleGroup " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_BUSINESS_RULE_TYPE);
		System.out.print(models3.length == 0 ? "" : "\n" + "BusinessRuleType " + models3.length);

		models3 = projectManager.getProject()
				.toModelElementArray(IModelElementFactory.MODEL_TYPE_BUSINESS_RULE_TYPE_CONTAINER);
		System.out.print(models3.length == 0 ? "" : "\n" + "BusinessRuleTypeContainer " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_CALLOUT);
		System.out.print(models3.length == 0 ? "" : "\n" + "Callout " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_CALL_BEHAVIOR_ACTION);
		System.out.print(models3.length == 0 ? "" : "\n" + "CallBehaviorAction " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_CALL_CONVERSATION);
		System.out.print(models3.length == 0 ? "" : "\n" + "CallConversation " + models3.length);

		models3 = projectManager.getProject()
				.toModelElementArray(IModelElementFactory.MODEL_TYPE_CALL_OPERATION_ACTION);
		System.out.print(models3.length == 0 ? "" : "\n" + "CallOperationAction " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_CALL_STATE);
		System.out.print(models3.length == 0 ? "" : "\n" + "CallState " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_CALL_TRIGGER);
		System.out.print(models3.length == 0 ? "" : "\n" + "CallTrigger " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_CANDIDATE_ITEM);
		System.out.print(models3.length == 0 ? "" : "\n" + "CandidateItem " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_CENTRAL_BUFFER_NODE);
		System.out.print(models3.length == 0 ? "" : "\n" + "CentralBufferNode " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_CHANGE_TRIGGER);
		System.out.print(models3.length == 0 ? "" : "\n" + "ChangeTrigger " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_CHART_CODE);
		System.out.print(models3.length == 0 ? "" : "\n" + "ChartCode " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_CHART_HEADER);
		System.out.print(models3.length == 0 ? "" : "\n" + "ChartHeader " + models3.length);

		models3 = projectManager.getProject()
				.toModelElementArray(IModelElementFactory.MODEL_TYPE_CHART_HEADER_CONTAINER);
		System.out.print(models3.length == 0 ? "" : "\n" + "ChartHeaderContainer " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_CHART_ITEM);
		System.out.print(models3.length == 0 ? "" : "\n" + "ChartItem " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_CHART_RELATIONSHIP);
		System.out.print(models3.length == 0 ? "" : "\n" + "ChartRelationship " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_CHART_TYPE);
		System.out.print(models3.length == 0 ? "" : "\n" + "ChartType " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_CHART_TYPE_CONTAINER);
		System.out.print(models3.length == 0 ? "" : "\n" + "ChartTypeContainer " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_CHECK_LIST_ITEM);
		System.out.print(models3.length == 0 ? "" : "\n" + "CheckListItem " + models3.length);

		models3 = projectManager.getProject()
				.toModelElementArray(IModelElementFactory.MODEL_TYPE_CHECK_LIST_ITEM_CONTAINER);
		System.out.print(models3.length == 0 ? "" : "\n" + "CheckListItemContainer " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_CHOICE);
		System.out.print(models3.length == 0 ? "" : "\n" + "Choice " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_CLASS);
		System.out.print(models3.length == 0 ? "" : "\n" + "Class " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_CLAUSE);
		System.out.print(models3.length == 0 ? "" : "\n" + "Clause " + models3.length);

		models3 = projectManager.getProject()
				.toModelElementArray(IModelElementFactory.MODEL_TYPE_CMMN_APPLICABILITY_RULE);
		System.out.print(models3.length == 0 ? "" : "\n" + "CMMNApplicabilityRule " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_CMMN_ASSOCIATION);
		System.out.print(models3.length == 0 ? "" : "\n" + "CMMNAssociation " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_CMMN_CASE);
		System.out.print(models3.length == 0 ? "" : "\n" + "CMMNCase " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_CMMN_CASE_FILE);
		System.out.print(models3.length == 0 ? "" : "\n" + "CMMNCaseFile " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_CMMN_CASE_FILE_ITEM);
		System.out.print(models3.length == 0 ? "" : "\n" + "CMMNCaseFileItem " + models3.length);

		models3 = projectManager.getProject()
				.toModelElementArray(IModelElementFactory.MODEL_TYPE_CMMN_CASE_FILE_ITEM_DEFINITION);
		System.out.print(models3.length == 0 ? "" : "\n" + "CMMNCaseFileItemDefinition " + models3.length);

		models3 = projectManager.getProject()
				.toModelElementArray(IModelElementFactory.MODEL_TYPE_CMMN_CASE_FILE_ITEM_ON_PART);
		System.out.print(models3.length == 0 ? "" : "\n" + "CMMNCaseFileItemOnPart " + models3.length);

		models3 = projectManager.getProject()
				.toModelElementArray(IModelElementFactory.MODEL_TYPE_CMMN_CASE_FILE_ITEM_START_TRIGGER);
		System.out.print(models3.length == 0 ? "" : "\n" + "CMMNCaseFileItemStartTrigger " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_CMMN_CASE_PARAMETER);
		System.out.print(models3.length == 0 ? "" : "\n" + "CMMNCaseParameter " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_CMMN_CASE_PLAN_MODEL);
		System.out.print(models3.length == 0 ? "" : "\n" + "CMMNCasePlanModel " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_CMMN_CASE_TASK);
		System.out.print(models3.length == 0 ? "" : "\n" + "CMMNCaseTask " + models3.length);

		models3 = projectManager.getProject()
				.toModelElementArray(IModelElementFactory.MODEL_TYPE_CMMN_CASE_TASK_DISCRETIONARY_ITEM);
		System.out.print(models3.length == 0 ? "" : "\n" + "CMMNCaseTaskDiscretionaryItem " + models3.length);

		models3 = projectManager.getProject()
				.toModelElementArray(IModelElementFactory.MODEL_TYPE_CMMN_CASE_TASK_PLAN_ITEM);
		System.out.print(models3.length == 0 ? "" : "\n" + "CMMNCaseTaskPlanItem " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_CMMN_DECISION);
		System.out.print(models3.length == 0 ? "" : "\n" + "CMMNDecision " + models3.length);

		models3 = projectManager.getProject()
				.toModelElementArray(IModelElementFactory.MODEL_TYPE_CMMN_DECISION_PARAMETER);
		System.out.print(models3.length == 0 ? "" : "\n" + "CMMNDecisionParameter " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_CMMN_DECISION_TASK);
		System.out.print(models3.length == 0 ? "" : "\n" + "CMMNDecisionTask " + models3.length);

		models3 = projectManager.getProject()
				.toModelElementArray(IModelElementFactory.MODEL_TYPE_CMMN_DECISION_TASK_DISCRETIONARY_ITEM);
		System.out.print(models3.length == 0 ? "" : "\n" + "CMMNDecisionTaskDiscretionaryItem " + models3.length);

		models3 = projectManager.getProject()
				.toModelElementArray(IModelElementFactory.MODEL_TYPE_CMMN_DECISION_TASK_PLAN_ITEM);
		System.out.print(models3.length == 0 ? "" : "\n" + "CMMNDecisionTaskPlanItem " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_CMMN_ENTRY_CRITERION);
		System.out.print(models3.length == 0 ? "" : "\n" + "CMMNEntryCriterion " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_CMMN_EXIT_CRITERION);
		System.out.print(models3.length == 0 ? "" : "\n" + "CMMNExitCriterion " + models3.length);

		models3 = projectManager.getProject()
				.toModelElementArray(IModelElementFactory.MODEL_TYPE_CMMN_GENERAL_EVENT_LISTENER);
		System.out.print(models3.length == 0 ? "" : "\n" + "CMMNGeneralEventListener " + models3.length);

		models3 = projectManager.getProject()
				.toModelElementArray(IModelElementFactory.MODEL_TYPE_CMMN_GENERAL_EVENT_LISTENER_PLAN_ITEM);
		System.out.print(models3.length == 0 ? "" : "\n" + "CMMNGeneralEventListenerPlanItem " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_CMMN_GENERAL_TASK);
		System.out.print(models3.length == 0 ? "" : "\n" + "CMMNGeneralTask " + models3.length);

		models3 = projectManager.getProject()
				.toModelElementArray(IModelElementFactory.MODEL_TYPE_CMMN_GENERAL_TASK_DISCRETIONARY_ITEM);
		System.out.print(models3.length == 0 ? "" : "\n" + "CMMNGeneralTaskDiscretionaryItem " + models3.length);

		models3 = projectManager.getProject()
				.toModelElementArray(IModelElementFactory.MODEL_TYPE_CMMN_GENERAL_TASK_PLAN_ITEM);
		System.out.print(models3.length == 0 ? "" : "\n" + "CMMNGeneralTaskPlanItem " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_CMMN_HUMAN_TASK);
		System.out.print(models3.length == 0 ? "" : "\n" + "CMMNHumanTask " + models3.length);

		models3 = projectManager.getProject()
				.toModelElementArray(IModelElementFactory.MODEL_TYPE_CMMN_HUMAN_TASK_DISCRETIONARY_ITEM);
		System.out.print(models3.length == 0 ? "" : "\n" + "CMMNHumanTaskDiscretionaryItem " + models3.length);

		models3 = projectManager.getProject()
				.toModelElementArray(IModelElementFactory.MODEL_TYPE_CMMN_HUMAN_TASK_PLAN_ITEM);
		System.out.print(models3.length == 0 ? "" : "\n" + "CMMNHumanTaskPlanItem " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_CMMN_IF_PART);
		System.out.print(models3.length == 0 ? "" : "\n" + "CMMNIfPart " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_CMMN_IMPORT);
		System.out.print(models3.length == 0 ? "" : "\n" + "CMMNImport " + models3.length);

		models3 = projectManager.getProject()
				.toModelElementArray(IModelElementFactory.MODEL_TYPE_CMMN_MANUAL_ACTIVATION_RULE);
		System.out.print(models3.length == 0 ? "" : "\n" + "CMMNManualActivationRule " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_CMMN_MILESTONE);
		System.out.print(models3.length == 0 ? "" : "\n" + "CMMNMilestone " + models3.length);

		models3 = projectManager.getProject()
				.toModelElementArray(IModelElementFactory.MODEL_TYPE_CMMN_MILESTONE_PLAN_ITEM);
		System.out.print(models3.length == 0 ? "" : "\n" + "CMMNMilestonePlanItem " + models3.length);

		models3 = projectManager.getProject()
				.toModelElementArray(IModelElementFactory.MODEL_TYPE_CMMN_PARAMETER_MAPPING);
		System.out.print(models3.length == 0 ? "" : "\n" + "CMMNParameterMapping " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_CMMN_PLANNING_TABLE);
		System.out.print(models3.length == 0 ? "" : "\n" + "CMMNPlanningTable " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_CMMN_PLAN_FRAGMENT);
		System.out.print(models3.length == 0 ? "" : "\n" + "CMMNPlanFragment " + models3.length);

		models3 = projectManager.getProject()
				.toModelElementArray(IModelElementFactory.MODEL_TYPE_CMMN_PLAN_FRAGMENT_DISCRETIONARY_ITEM);
		System.out.print(models3.length == 0 ? "" : "\n" + "CMMNPlanFragmentDiscretionaryItem " + models3.length);

		models3 = projectManager.getProject()
				.toModelElementArray(IModelElementFactory.MODEL_TYPE_CMMN_PLAN_ITEM_CONTROL);
		System.out.print(models3.length == 0 ? "" : "\n" + "CMMNPlanItemControl " + models3.length);

		models3 = projectManager.getProject()
				.toModelElementArray(IModelElementFactory.MODEL_TYPE_CMMN_PLAN_ITEM_ON_PART);
		System.out.print(models3.length == 0 ? "" : "\n" + "CMMNPlanItemOnPart " + models3.length);

		models3 = projectManager.getProject()
				.toModelElementArray(IModelElementFactory.MODEL_TYPE_CMMN_PLAN_ITEM_START_TRIGGER);
		System.out.print(models3.length == 0 ? "" : "\n" + "CMMNPlanItemStartTrigger " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_CMMN_PROCESS);
		System.out.print(models3.length == 0 ? "" : "\n" + "CMMNProcess " + models3.length);

		models3 = projectManager.getProject()
				.toModelElementArray(IModelElementFactory.MODEL_TYPE_CMMN_PROCESS_PARAMETER);
		System.out.print(models3.length == 0 ? "" : "\n" + "CMMNProcessParameter " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_CMMN_PROCESS_TASK);
		System.out.print(models3.length == 0 ? "" : "\n" + "CMMNProcessTask " + models3.length);

		models3 = projectManager.getProject()
				.toModelElementArray(IModelElementFactory.MODEL_TYPE_CMMN_PROCESS_TASK_DISCRETIONARY_ITEM);
		System.out.print(models3.length == 0 ? "" : "\n" + "CMMNProcessTaskDiscretionaryItem " + models3.length);

		models3 = projectManager.getProject()
				.toModelElementArray(IModelElementFactory.MODEL_TYPE_CMMN_PROCESS_TASK_PLAN_ITEM);
		System.out.print(models3.length == 0 ? "" : "\n" + "CMMNProcessTaskPlanItem " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_CMMN_PROPERTY);
		System.out.print(models3.length == 0 ? "" : "\n" + "CMMNProperty " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_CMMN_REPETITION_RULE);
		System.out.print(models3.length == 0 ? "" : "\n" + "CMMNRepetitionRule " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_CMMN_REQUIRED_RULE);
		System.out.print(models3.length == 0 ? "" : "\n" + "CMMNRequiredRule " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_CMMN_ROLE);
		System.out.print(models3.length == 0 ? "" : "\n" + "CMMNRole " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_CMMN_SENTRY);
		System.out.print(models3.length == 0 ? "" : "\n" + "CMMNSentry " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_CMMN_STAGE);
		System.out.print(models3.length == 0 ? "" : "\n" + "CMMNStage " + models3.length);

		models3 = projectManager.getProject()
				.toModelElementArray(IModelElementFactory.MODEL_TYPE_CMMN_STAGE_DISCRETIONARY_ITEM);
		System.out.print(models3.length == 0 ? "" : "\n" + "CMMNStageDiscretionaryItem " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_CMMN_STAGE_PLAN_ITEM);
		System.out.print(models3.length == 0 ? "" : "\n" + "CMMNStagePlanItem " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_CMMN_TEXT_ANNOTATION);
		System.out.print(models3.length == 0 ? "" : "\n" + "CMMNTextAnnotation " + models3.length);

		models3 = projectManager.getProject()
				.toModelElementArray(IModelElementFactory.MODEL_TYPE_CMMN_TIMER_EVENT_LISTENER);
		System.out.print(models3.length == 0 ? "" : "\n" + "CMMNTimerEventListener " + models3.length);

		models3 = projectManager.getProject()
				.toModelElementArray(IModelElementFactory.MODEL_TYPE_CMMN_TIMER_EVENT_LISTENER_PLAN_ITEM);
		System.out.print(models3.length == 0 ? "" : "\n" + "CMMNTimerEventListenerPlanItem " + models3.length);

		models3 = projectManager.getProject()
				.toModelElementArray(IModelElementFactory.MODEL_TYPE_CMMN_USER_EVENT_LISTENER);
		System.out.print(models3.length == 0 ? "" : "\n" + "CMMNUserEventListener " + models3.length);

		models3 = projectManager.getProject()
				.toModelElementArray(IModelElementFactory.MODEL_TYPE_CMMN_USER_EVENT_LISTENER_PLAN_ITEM);
		System.out.print(models3.length == 0 ? "" : "\n" + "CMMNUserEventListenerPlanItem " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_COLLABORATION);
		System.out.print(models3.length == 0 ? "" : "\n" + "Collaboration " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_COLLABORATION2);
		System.out.print(models3.length == 0 ? "" : "\n" + "Collaboration2 " + models3.length);

		models3 = projectManager.getProject()
				.toModelElementArray(IModelElementFactory.MODEL_TYPE_COLLABORATION_OCCURRENCE);
		System.out.print(models3.length == 0 ? "" : "\n" + "CollaborationOccurrence " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_COLUMN);
		System.out.print(models3.length == 0 ? "" : "\n" + "Column " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_COMBINED_FRAGMENT);
		System.out.print(models3.length == 0 ? "" : "\n" + "CombinedFragment " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_COMMENT);
		System.out.print(models3.length == 0 ? "" : "\n" + "Comment " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_COMPONENT);
		System.out.print(models3.length == 0 ? "" : "\n" + "Component " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_COMPONENT_INSTANCE);
		System.out.print(models3.length == 0 ? "" : "\n" + "ComponentInstance " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_COMPONENT_OBJECT);
		System.out.print(models3.length == 0 ? "" : "\n" + "ComponentObject " + models3.length);

		models3 = projectManager.getProject()
				.toModelElementArray(IModelElementFactory.MODEL_TYPE_COMPOSITE_ASSOCIATION_DB_COLUMN);
		System.out.print(models3.length == 0 ? "" : "\n" + "CompositeAssociationDBColumn " + models3.length);

		models3 = projectManager.getProject()
				.toModelElementArray(IModelElementFactory.MODEL_TYPE_COMPOSITE_DB_TABLE_DB_COLUMN);
		System.out.print(models3.length == 0 ? "" : "\n" + "CompositeDBTableDBColumn " + models3.length);

		models3 = projectManager.getProject()
				.toModelElementArray(IModelElementFactory.MODEL_TYPE_COMPOSITE_DB_TABLE_FOREIGN_KEY);
		System.out.print(models3.length == 0 ? "" : "\n" + "CompositeDBTableForeignKey " + models3.length);

		models3 = projectManager.getProject()
				.toModelElementArray(IModelElementFactory.MODEL_TYPE_COMPOSITE_VALUE_SPECIFICATION);
		System.out.print(models3.length == 0 ? "" : "\n" + "CompositeValueSpecification " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_CONCURRENT);
		System.out.print(models3.length == 0 ? "" : "\n" + "Concurrent " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_CONCURRENT_STATE);
		System.out.print(models3.length == 0 ? "" : "\n" + "ConcurrentState " + models3.length);

		models3 = projectManager.getProject()
				.toModelElementArray(IModelElementFactory.MODEL_TYPE_CONCURRENT_STATE_REGION);
		System.out.print(models3.length == 0 ? "" : "\n" + "ConcurrentStateRegion " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_CONDITIONAL_NODE);
		System.out.print(models3.length == 0 ? "" : "\n" + "ConditionalNode " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_CONNECTOR);
		System.out.print(models3.length == 0 ? "" : "\n" + "Connector " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_CONNECTOR_END);
		System.out.print(models3.length == 0 ? "" : "\n" + "ConnectorEnd " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_CONSTRAINT);
		System.out.print(models3.length == 0 ? "" : "\n" + "Constraint " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_CONSTRAINT_ELEMENT);
		System.out.print(models3.length == 0 ? "" : "\n" + "ConstraintElement " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_CONTINUATION);
		System.out.print(models3.length == 0 ? "" : "\n" + "Continuation " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_CONTROL_FLOW);
		System.out.print(models3.length == 0 ? "" : "\n" + "ControlFlow " + models3.length);

		models3 = projectManager.getProject()
				.toModelElementArray(IModelElementFactory.MODEL_TYPE_CONVERSATION_COMMUNICATION);
		System.out.print(models3.length == 0 ? "" : "\n" + "ConversationCommunication " + models3.length);

		models3 = projectManager.getProject()
				.toModelElementArray(IModelElementFactory.MODEL_TYPE_CONVERSATION_CONTAINER);
		System.out.print(models3.length == 0 ? "" : "\n" + "ConversationContainer " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_CONVERSATION_LINK);
		System.out.print(models3.length == 0 ? "" : "\n" + "ConversationLink " + models3.length);

		models3 = projectManager.getProject()
				.toModelElementArray(IModelElementFactory.MODEL_TYPE_CPP_ATTRIBUTE_CODE_DETAIL);
		System.out.print(models3.length == 0 ? "" : "\n" + "CPPAttributeCodeDetail " + models3.length);

		models3 = projectManager.getProject()
				.toModelElementArray(IModelElementFactory.MODEL_TYPE_CPP_OPERATION_CODE_DETAIL);
		System.out.print(models3.length == 0 ? "" : "\n" + "CPPOperationCodeDetail " + models3.length);

		models3 = projectManager.getProject()
				.toModelElementArray(IModelElementFactory.MODEL_TYPE_CPP_PARAMETER_CODE_DETAIL);
		System.out.print(models3.length == 0 ? "" : "\n" + "CPPParameterCodeDetail " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_CRC_ATTRIBUTE);
		System.out.print(models3.length == 0 ? "" : "\n" + "CRCAttribute " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_CRC_CARD);
		System.out.print(models3.length == 0 ? "" : "\n" + "CRCCard " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_CRC_RESPONSIBILITY);
		System.out.print(models3.length == 0 ? "" : "\n" + "CRCResponsibility " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_DATA_STORE_NODE);
		System.out.print(models3.length == 0 ? "" : "\n" + "DataStoreNode " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_DATA_TYPE);
		System.out.print(models3.length == 0 ? "" : "\n" + "DataType " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_DB_CHECK_CONSTRAINT);
		System.out.print(models3.length == 0 ? "" : "\n" + "DBCheckConstraint " + models3.length);

		models3 = projectManager.getProject()
				.toModelElementArray(IModelElementFactory.MODEL_TYPE_DB_CHECK_CONSTRAINT_DOMAIN);
		System.out.print(models3.length == 0 ? "" : "\n" + "DBCheckConstraintDomain " + models3.length);

		models3 = projectManager.getProject()
				.toModelElementArray(IModelElementFactory.MODEL_TYPE_DB_CHECK_CONSTRAINT_DOMAIN_RELATIONSHIP);
		System.out.print(models3.length == 0 ? "" : "\n" + "DBCheckConstraintDomainRelationship " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_DB_COLUMN);
		System.out.print(models3.length == 0 ? "" : "\n" + "DBColumn " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_DB_COLUMN_DOMAIN);
		System.out.print(models3.length == 0 ? "" : "\n" + "DBColumnDomain " + models3.length);

		models3 = projectManager.getProject()
				.toModelElementArray(IModelElementFactory.MODEL_TYPE_DB_COLUMN_DOMAIN_CONTAINER);
		System.out.print(models3.length == 0 ? "" : "\n" + "DBColumnDomainContainer " + models3.length);

		models3 = projectManager.getProject()
				.toModelElementArray(IModelElementFactory.MODEL_TYPE_DB_COLUMN_DOMAIN_REF_RELATIONSHIP);
		System.out.print(models3.length == 0 ? "" : "\n" + "DBColumnDomainRefRelationship " + models3.length);

		models3 = projectManager.getProject()
				.toModelElementArray(IModelElementFactory.MODEL_TYPE_DB_COLUMN_DOMAIN_RELATIONSHIP);
		System.out.print(models3.length == 0 ? "" : "\n" + "DBColumnDomainRelationship " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_DB_COLUMN_OPTION);
		System.out.print(models3.length == 0 ? "" : "\n" + "DBColumnOption " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_DB_COLUMN_ORDER);
		System.out.print(models3.length == 0 ? "" : "\n" + "DBColumnOrder " + models3.length);

		models3 = projectManager.getProject()
				.toModelElementArray(IModelElementFactory.MODEL_TYPE_DB_COLUMN_ORDER_DOMAIN);
		System.out.print(models3.length == 0 ? "" : "\n" + "DBColumnOrderDomain " + models3.length);

		models3 = projectManager.getProject()
				.toModelElementArray(IModelElementFactory.MODEL_TYPE_DB_COLUMN_ORDER_DOMAIN_RELATIONSHIP);
		System.out.print(models3.length == 0 ? "" : "\n" + "DBColumnOrderDomainRelationship " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_DB_COLUMN_USER_TYPE);
		System.out.print(models3.length == 0 ? "" : "\n" + "DBColumnUserType " + models3.length);

		models3 = projectManager.getProject()
				.toModelElementArray(IModelElementFactory.MODEL_TYPE_DB_COLUMN_USER_TYPE_DOMAIN);
		System.out.print(models3.length == 0 ? "" : "\n" + "DBColumnUserTypeDomain " + models3.length);

		models3 = projectManager.getProject()
				.toModelElementArray(IModelElementFactory.MODEL_TYPE_DB_COLUMN_USER_TYPE_DOMAIN_RELATIONSHIP);
		System.out.print(models3.length == 0 ? "" : "\n" + "DBColumnUserTypeDomainRelationship " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_DB_FOREIGN_KEY);
		System.out.print(models3.length == 0 ? "" : "\n" + "DBForeignKey " + models3.length);

		models3 = projectManager.getProject()
				.toModelElementArray(IModelElementFactory.MODEL_TYPE_DB_FOREIGN_KEY_CONSTRAINT);
		System.out.print(models3.length == 0 ? "" : "\n" + "DBForeignKeyConstraint " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_DB_FULL_TEXT_INDEX);
		System.out.print(models3.length == 0 ? "" : "\n" + "DBFullTextIndex " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_DB_INDEX);
		System.out.print(models3.length == 0 ? "" : "\n" + "DBIndex " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_DB_INDEX_DOMAIN);
		System.out.print(models3.length == 0 ? "" : "\n" + "DBIndexDomain " + models3.length);

		models3 = projectManager.getProject()
				.toModelElementArray(IModelElementFactory.MODEL_TYPE_DB_INDEX_DOMAIN_RELATIONSHIP);
		System.out.print(models3.length == 0 ? "" : "\n" + "DBIndexDomainRelationship " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_DB_LOOKUP_CONTAINER);
		System.out.print(models3.length == 0 ? "" : "\n" + "DBLookupContainer " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_DB_SCHEMA_LOOKUP);
		System.out.print(models3.length == 0 ? "" : "\n" + "DBSchemaLookup " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_DB_SEQUENCE);
		System.out.print(models3.length == 0 ? "" : "\n" + "DBSequence " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_DB_TABLE);
		System.out.print(models3.length == 0 ? "" : "\n" + "DBTable " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_DB_TABLESPACE_LOOKUP);
		System.out.print(models3.length == 0 ? "" : "\n" + "DBTablespaceLookup " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_DB_TABLE_DOMAIN);
		System.out.print(models3.length == 0 ? "" : "\n" + "DBTableDomain " + models3.length);

		models3 = projectManager.getProject()
				.toModelElementArray(IModelElementFactory.MODEL_TYPE_DB_TABLE_DOMAIN_CONTAINER);
		System.out.print(models3.length == 0 ? "" : "\n" + "DBTableDomainContainer " + models3.length);

		models3 = projectManager.getProject()
				.toModelElementArray(IModelElementFactory.MODEL_TYPE_DB_TABLE_DOMAIN_RELATIONSHIP);
		System.out.print(models3.length == 0 ? "" : "\n" + "DBTableDomainRelationship " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_DB_TRIGGER);
		System.out.print(models3.length == 0 ? "" : "\n" + "DBTrigger " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_DB_TRIGGER_CONTAINER);
		System.out.print(models3.length == 0 ? "" : "\n" + "DBTriggerContainer " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_DB_UNIQUE_CONSTRAINT);
		System.out.print(models3.length == 0 ? "" : "\n" + "DBUniqueConstraint " + models3.length);

		models3 = projectManager.getProject()
				.toModelElementArray(IModelElementFactory.MODEL_TYPE_DB_UNIQUE_CONSTRAINT_DOMAIN);
		System.out.print(models3.length == 0 ? "" : "\n" + "DBUniqueConstraintDomain " + models3.length);

		models3 = projectManager.getProject()
				.toModelElementArray(IModelElementFactory.MODEL_TYPE_DB_UNIQUE_CONSTRAINT_DOMAIN_RELATIONSHIP);
		System.out.print(models3.length == 0 ? "" : "\n" + "DBUniqueConstraintDomainRelationship " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_DB_VIEW);
		System.out.print(models3.length == 0 ? "" : "\n" + "DBView " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_DB_VIEW_COLUMN);
		System.out.print(models3.length == 0 ? "" : "\n" + "DBViewColumn " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_DB_VIEW_FILTER);
		System.out.print(models3.length == 0 ? "" : "\n" + "DBViewFilter " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_DB_VIEW_FILTER_GROUP);
		System.out.print(models3.length == 0 ? "" : "\n" + "DBViewFilterGroup " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_DB_VIEW_JOIN);
		System.out.print(models3.length == 0 ? "" : "\n" + "DBViewJoin " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_DB_VIEW_SORT_BY);
		System.out.print(models3.length == 0 ? "" : "\n" + "DBViewSortBy " + models3.length);

		models3 = projectManager.getProject()
				.toModelElementArray(IModelElementFactory.MODEL_TYPE_DB_VIEW_SORT_BY_COLUMN);
		System.out.print(models3.length == 0 ? "" : "\n" + "DBViewSortByColumn " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_DB_VIEW_TABLE);
		System.out.print(models3.length == 0 ? "" : "\n" + "DBViewTable " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_DECISION_NODE);
		System.out.print(models3.length == 0 ? "" : "\n" + "DecisionNode " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_DECISION_POINT);
		System.out.print(models3.length == 0 ? "" : "\n" + "DecisionPoint " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_DEEP_HISTORY);
		System.out.print(models3.length == 0 ? "" : "\n" + "DeepHistory " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_DEFAULT_TAGGED_VALUE);
		System.out.print(models3.length == 0 ? "" : "\n" + "DefaultTaggedValue " + models3.length);

		models3 = projectManager.getProject()
				.toModelElementArray(IModelElementFactory.MODEL_TYPE_DEFAULT_TAGGED_VALUE_CONTAINER);
		System.out.print(models3.length == 0 ? "" : "\n" + "DefaultTaggedValueContainer " + models3.length);

		models3 = projectManager.getProject()
				.toModelElementArray(IModelElementFactory.MODEL_TYPE_DEFERRABLE_EVENT_CONTAINER);
		System.out.print(models3.length == 0 ? "" : "\n" + "DeferrableEventContainer " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_DEPENDENCY);
		System.out.print(models3.length == 0 ? "" : "\n" + "Dependency " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_DEPLOYMENT);
		System.out.print(models3.length == 0 ? "" : "\n" + "Deployment " + models3.length);

		models3 = projectManager.getProject()
				.toModelElementArray(IModelElementFactory.MODEL_TYPE_DEPLOYMENT_SPECIFICATION);
		System.out.print(models3.length == 0 ? "" : "\n" + "DeploymentSpecification " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_DF_DATA_FLOW);
		System.out.print(models3.length == 0 ? "" : "\n" + "DFDataFlow " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_DF_DATA_STORE);
		System.out.print(models3.length == 0 ? "" : "\n" + "DFDataStore " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_DF_EXTERNAL_ENTITY);
		System.out.print(models3.length == 0 ? "" : "\n" + "DFExternalEntity " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_DF_PROCESS);
		System.out.print(models3.length == 0 ? "" : "\n" + "DFProcess " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_DIAGRAM_OVERVIEW);
		System.out.print(models3.length == 0 ? "" : "\n" + "DiagramOverview " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_DIAGRAM_VOICE);
		System.out.print(models3.length == 0 ? "" : "\n" + "DiagramVoice " + models3.length);

		models3 = projectManager.getProject()
				.toModelElementArray(IModelElementFactory.MODEL_TYPE_DIAGRAM_VOICE_CONTAINER);
		System.out.print(models3.length == 0 ? "" : "\n" + "DiagramVoiceContainer " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_DM_BUSINESS_DECISION);
		System.out.print(models3.length == 0 ? "" : "\n" + "DMBusinessDecision " + models3.length);

		models3 = projectManager.getProject()
				.toModelElementArray(IModelElementFactory.MODEL_TYPE_DM_BUSINESS_LOGIC_INSTANCE);
		System.out.print(models3.length == 0 ? "" : "\n" + "DMBusinessLogicInstance " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_DM_CONCLUSION_VALUE);
		System.out.print(models3.length == 0 ? "" : "\n" + "DMConclusionValue " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_DM_CONDITION);
		System.out.print(models3.length == 0 ? "" : "\n" + "DMCondition " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_DM_CONDITION_CELL);
		System.out.print(models3.length == 0 ? "" : "\n" + "DMConditionCell " + models3.length);

		models3 = projectManager.getProject()
				.toModelElementArray(IModelElementFactory.MODEL_TYPE_DM_CONDITION_OPERATOR_VALUE_CONTAINER);
		System.out.print(models3.length == 0 ? "" : "\n" + "DMConditionOperatorValueContainer " + models3.length);

		models3 = projectManager.getProject()
				.toModelElementArray(IModelElementFactory.MODEL_TYPE_DM_CONDITION_RESULT_VALUE_CONTAINER);
		System.out.print(models3.length == 0 ? "" : "\n" + "DMConditionResultValueContainer " + models3.length);

		models3 = projectManager.getProject()
				.toModelElementArray(IModelElementFactory.MODEL_TYPE_DM_DECISION_RULE_FAMILY_RELATIONSHIP);
		System.out.print(models3.length == 0 ? "" : "\n" + "DMDecisionRuleFamilyRelationship " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_DM_INFLUENCE);
		System.out.print(models3.length == 0 ? "" : "\n" + "DMInfluence " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_DM_RULE_FAMILY);
		System.out.print(models3.length == 0 ? "" : "\n" + "DMRuleFamily " + models3.length);

		models3 = projectManager.getProject()
				.toModelElementArray(IModelElementFactory.MODEL_TYPE_DM_SUB_CONDITION_CELL);
		System.out.print(models3.length == 0 ? "" : "\n" + "DMSubConditionCell " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_DOMAIN_CONTAINER);
		System.out.print(models3.length == 0 ? "" : "\n" + "DomainContainer " + models3.length);

		models3 = projectManager.getProject()
				.toModelElementArray(IModelElementFactory.MODEL_TYPE_DOT_NET_ATTRIBUTE_CODE_DETAIL);
		System.out.print(models3.length == 0 ? "" : "\n" + "DotNetAttributeCodeDetail " + models3.length);

		models3 = projectManager.getProject()
				.toModelElementArray(IModelElementFactory.MODEL_TYPE_DOT_NET_CLASS_CODE_DETAIL);
		System.out.print(models3.length == 0 ? "" : "\n" + "DotNetClassCodeDetail " + models3.length);

		models3 = projectManager.getProject()
				.toModelElementArray(IModelElementFactory.MODEL_TYPE_DOT_NET_GENERALIZATION_CODE_DETAIL);
		System.out.print(models3.length == 0 ? "" : "\n" + "DotNetGeneralizationCodeDetail " + models3.length);

		models3 = projectManager.getProject()
				.toModelElementArray(IModelElementFactory.MODEL_TYPE_DOT_NET_OPERATION_CODE_DETAIL);
		System.out.print(models3.length == 0 ? "" : "\n" + "DotNetOperationCodeDetail " + models3.length);

		models3 = projectManager.getProject()
				.toModelElementArray(IModelElementFactory.MODEL_TYPE_DOT_NET_PARAMETER_CODE_DETAIL);
		System.out.print(models3.length == 0 ? "" : "\n" + "DotNetParameterCodeDetail " + models3.length);

		models3 = projectManager.getProject()
				.toModelElementArray(IModelElementFactory.MODEL_TYPE_DOT_NET_REALIZATION_CODE_DETAIL);
		System.out.print(models3.length == 0 ? "" : "\n" + "DotNetRealizationCodeDetail " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_DTB_ACTION);
		System.out.print(models3.length == 0 ? "" : "\n" + "DTBAction " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_DTB_CONDITION);
		System.out.print(models3.length == 0 ? "" : "\n" + "DTBCondition " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_DTB_DECISION_TABLE);
		System.out.print(models3.length == 0 ? "" : "\n" + "DTBDecisionTable " + models3.length);

		models3 = projectManager.getProject()
				.toModelElementArray(IModelElementFactory.MODEL_TYPE_DTB_DECISION_TABLE_ASSOCIATION);
		System.out.print(models3.length == 0 ? "" : "\n" + "DTBDecisionTableAssociation " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_DTB_RULE);
		System.out.print(models3.length == 0 ? "" : "\n" + "DTBRule " + models3.length);

		models3 = projectManager.getProject()
				.toModelElementArray(IModelElementFactory.MODEL_TYPE_DTB_RULE_ACTION_VALUE);
		System.out.print(models3.length == 0 ? "" : "\n" + "DTBRuleActionValue " + models3.length);

		models3 = projectManager.getProject()
				.toModelElementArray(IModelElementFactory.MODEL_TYPE_DTB_RULE_CONDITION_VALUE);
		System.out.print(models3.length == 0 ? "" : "\n" + "DTBRuleConditionValue " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_DURATION_CONSTRAINT);
		System.out.print(models3.length == 0 ? "" : "\n" + "DurationConstraint " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_DYNAMIC_POINT);
		System.out.print(models3.length == 0 ? "" : "\n" + "DynamicPoint " + models3.length);

		models3 = projectManager.getProject()
				.toModelElementArray(IModelElementFactory.MODEL_TYPE_EJB_ASSOCIATION_END_CODE_DETAIL);
		System.out.print(models3.length == 0 ? "" : "\n" + "EJBAssociationEndCodeDetail " + models3.length);

		models3 = projectManager.getProject()
				.toModelElementArray(IModelElementFactory.MODEL_TYPE_EJB_ATTRIBUTE_CODE_DETAIL);
		System.out.print(models3.length == 0 ? "" : "\n" + "EJBAttributeCodeDetail " + models3.length);

		models3 = projectManager.getProject()
				.toModelElementArray(IModelElementFactory.MODEL_TYPE_EJB_CLASS_CODE_DETAIL);
		System.out.print(models3.length == 0 ? "" : "\n" + "EJBClassCodeDetail " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_EJB_DEPLOY_DETAIL);
		System.out.print(models3.length == 0 ? "" : "\n" + "EJBDeployDetail " + models3.length);

		models3 = projectManager.getProject()
				.toModelElementArray(IModelElementFactory.MODEL_TYPE_EJB_OPERATION_CODE_DETAIL);
		System.out.print(models3.length == 0 ? "" : "\n" + "EJBOperationCodeDetail " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_ENTITY_RECORD);
		System.out.print(models3.length == 0 ? "" : "\n" + "EntityRecord " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_ENTITY_RECORD_CELL);
		System.out.print(models3.length == 0 ? "" : "\n" + "EntityRecordCell " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_ENTITY_RECORD_COLUMN);
		System.out.print(models3.length == 0 ? "" : "\n" + "EntityRecordColumn " + models3.length);

		models3 = projectManager.getProject()
				.toModelElementArray(IModelElementFactory.MODEL_TYPE_ENTITY_RECORD_CONTAINER);
		System.out.print(models3.length == 0 ? "" : "\n" + "EntityRecordContainer " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_ENTRY_POINT);
		System.out.print(models3.length == 0 ? "" : "\n" + "EntryPoint " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_ENUMERATION_LITERAL);
		System.out.print(models3.length == 0 ? "" : "\n" + "EnumerationLiteral " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_EPCXOR_OPERATOR);
		System.out.print(models3.length == 0 ? "" : "\n" + "EPCXOROperator " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_EPC_AND_OPERATOR);
		System.out.print(models3.length == 0 ? "" : "\n" + "EPCAndOperator " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_EPC_CONTROL_FLOW);
		System.out.print(models3.length == 0 ? "" : "\n" + "EPCControlFlow " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_EPC_EVENT);
		System.out.print(models3.length == 0 ? "" : "\n" + "EPCEvent " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_EPC_FUNCTION);
		System.out.print(models3.length == 0 ? "" : "\n" + "EPCFunction " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_EPC_INFORMATION_FLOW);
		System.out.print(models3.length == 0 ? "" : "\n" + "EPCInformationFlow " + models3.length);

		models3 = projectManager.getProject()
				.toModelElementArray(IModelElementFactory.MODEL_TYPE_EPC_INFORMATION_RESOURCE);
		System.out.print(models3.length == 0 ? "" : "\n" + "EPCInformationResource " + models3.length);

		models3 = projectManager.getProject()
				.toModelElementArray(IModelElementFactory.MODEL_TYPE_EPC_ORGANIZATION_UNIT);
		System.out.print(models3.length == 0 ? "" : "\n" + "EPCOrganizationUnit " + models3.length);

		models3 = projectManager.getProject()
				.toModelElementArray(IModelElementFactory.MODEL_TYPE_EPC_ORGANIZATION_UNIT_ASSIGNMENT);
		System.out.print(models3.length == 0 ? "" : "\n" + "EPCOrganizationUnitAssignment " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_EPC_OR_OPERATOR);
		System.out.print(models3.length == 0 ? "" : "\n" + "EPCOrOperator " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_EPC_PROCESS_PATH);
		System.out.print(models3.length == 0 ? "" : "\n" + "EPCProcessPath " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_EPC_ROLE);
		System.out.print(models3.length == 0 ? "" : "\n" + "EPCRole " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_EPC_SYSTEM);
		System.out.print(models3.length == 0 ? "" : "\n" + "EPCSystem " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_EVENT);
		System.out.print(models3.length == 0 ? "" : "\n" + "Event " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_EVENT_TYPE_CALL);
		System.out.print(models3.length == 0 ? "" : "\n" + "EventTypeCall " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_EVENT_TYPE_CHANGE);
		System.out.print(models3.length == 0 ? "" : "\n" + "EventTypeChange " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_EVENT_TYPE_SIGNAL);
		System.out.print(models3.length == 0 ? "" : "\n" + "EventTypeSignal " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_EVENT_TYPE_TIME);
		System.out.print(models3.length == 0 ? "" : "\n" + "EventTypeTime " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_EXCEPTION_HANDLER);
		System.out.print(models3.length == 0 ? "" : "\n" + "ExceptionHandler " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_EXECUTION_OCCURRENCE);
		System.out.print(models3.length == 0 ? "" : "\n" + "ExecutionOccurrence " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_EXIT_POINT);
		System.out.print(models3.length == 0 ? "" : "\n" + "ExitPoint " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_EXPANSION_NODE);
		System.out.print(models3.length == 0 ? "" : "\n" + "ExpansionNode " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_EXPANSION_REGION);
		System.out.print(models3.length == 0 ? "" : "\n" + "ExpansionRegion " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_EXTEND);
		System.out.print(models3.length == 0 ? "" : "\n" + "Extend " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_EXTENSION);
		System.out.print(models3.length == 0 ? "" : "\n" + "Extension " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_EXTENSION_POINT);
		System.out.print(models3.length == 0 ? "" : "\n" + "ExtensionPoint " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_FACT);
		System.out.print(models3.length == 0 ? "" : "\n" + "Fact " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_FACT_ROLE);
		System.out.print(models3.length == 0 ? "" : "\n" + "FactRole " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_FACT_TYPE);
		System.out.print(models3.length == 0 ? "" : "\n" + "FactType " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_FACT_TYPE_ROLE);
		System.out.print(models3.length == 0 ? "" : "\n" + "FactTypeRole " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_FILE_OBJECT);
		System.out.print(models3.length == 0 ? "" : "\n" + "FileObject " + models3.length);

		models3 = projectManager.getProject()
				.toModelElementArray(IModelElementFactory.MODEL_TYPE_FILE_OBJECT_CONTAINER);
		System.out.print(models3.length == 0 ? "" : "\n" + "FileObjectContainer " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_FINAL_STATE);
		System.out.print(models3.length == 0 ? "" : "\n" + "FinalState " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_FINAL_STATE2);
		System.out.print(models3.length == 0 ? "" : "\n" + "FinalState2 " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_FI_DIAGRAM);
		System.out.print(models3.length == 0 ? "" : "\n" + "FIDiagram " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_FI_ELEMENT);
		System.out.print(models3.length == 0 ? "" : "\n" + "FIElement " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_FI_TEXT);
		System.out.print(models3.length == 0 ? "" : "\n" + "FIText " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_FI_TEXT_INSTANCE);
		System.out.print(models3.length == 0 ? "" : "\n" + "FITextInstance " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_FLOW_FINAL_NODE);
		System.out.print(models3.length == 0 ? "" : "\n" + "FlowFinalNode " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_FLOW_OF_EVENT_ITEM);
		System.out.print(models3.length == 0 ? "" : "\n" + "FlowOfEventItem " + models3.length);

		models3 = projectManager.getProject()
				.toModelElementArray(IModelElementFactory.MODEL_TYPE_FLOW_OF_EVENT_ITEM_CONTAINER);
		System.out.print(models3.length == 0 ? "" : "\n" + "FlowOfEventItemContainer " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_FOREIGN_KEY);
		System.out.print(models3.length == 0 ? "" : "\n" + "ForeignKey " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_FORK);
		System.out.print(models3.length == 0 ? "" : "\n" + "Fork " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_FORK_NODE);
		System.out.print(models3.length == 0 ? "" : "\n" + "ForkNode " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_FRAME);
		System.out.print(models3.length == 0 ? "" : "\n" + "Frame " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_FREEHAND);
		System.out.print(models3.length == 0 ? "" : "\n" + "Freehand " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_GATE);
		System.out.print(models3.length == 0 ? "" : "\n" + "Gate " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_GENERALIZATION);
		System.out.print(models3.length == 0 ? "" : "\n" + "Generalization " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_GENERALIZATION_SET);
		System.out.print(models3.length == 0 ? "" : "\n" + "GeneralizationSet " + models3.length);

		models3 = projectManager.getProject()
				.toModelElementArray(IModelElementFactory.MODEL_TYPE_GENERAL_DURATION_CONSTRAINT);
		System.out.print(models3.length == 0 ? "" : "\n" + "GeneralDurationConstraint " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_GENERIC_CONNECTOR);
		System.out.print(models3.length == 0 ? "" : "\n" + "GenericConnector " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_GENERIC_SHAPE);
		System.out.print(models3.length == 0 ? "" : "\n" + "GenericShape " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_GLOSSARY);
		System.out.print(models3.length == 0 ? "" : "\n" + "Glossary " + models3.length);

		models3 = projectManager.getProject()
				.toModelElementArray(IModelElementFactory.MODEL_TYPE_GLOSSARY_FACT_TYPE_ASSOCIATION);
		System.out.print(models3.length == 0 ? "" : "\n" + "GlossaryFactTypeAssociation " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_GLOSSARY_LABEL);
		System.out.print(models3.length == 0 ? "" : "\n" + "GlossaryLabel " + models3.length);

		models3 = projectManager.getProject()
				.toModelElementArray(IModelElementFactory.MODEL_TYPE_GLOSSARY_TERM_ATTRIBUTE);
		System.out.print(models3.length == 0 ? "" : "\n" + "GlossaryTermAttribute " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_GRAPHIC_SHAPE);
		System.out.print(models3.length == 0 ? "" : "\n" + "GraphicShape " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_GRID_DIAGRAM_COLUMN);
		System.out.print(models3.length == 0 ? "" : "\n" + "GridDiagramColumn " + models3.length);

		models3 = projectManager.getProject()
				.toModelElementArray(IModelElementFactory.MODEL_TYPE_GRID_DIAGRAM_COLUMN_CONTAINER);
		System.out.print(models3.length == 0 ? "" : "\n" + "GridDiagramColumnContainer " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_GUARD);
		System.out.print(models3.length == 0 ? "" : "\n" + "Guard " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_HISTORY_STATE);
		System.out.print(models3.length == 0 ? "" : "\n" + "HistoryState " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_HTML_NOTE);
		System.out.print(models3.length == 0 ? "" : "\n" + "HTMLNote " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_IMAGE_SHAPE);
		System.out.print(models3.length == 0 ? "" : "\n" + "ImageShape " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_IMPL_MODEL);
		System.out.print(models3.length == 0 ? "" : "\n" + "ImplModel " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_INCLUDE);
		System.out.print(models3.length == 0 ? "" : "\n" + "Include " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_INDEX);
		System.out.print(models3.length == 0 ? "" : "\n" + "Index " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_INFORMATION_FLOW);
		System.out.print(models3.length == 0 ? "" : "\n" + "InformationFlow " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_INFORMATION_ITEM);
		System.out.print(models3.length == 0 ? "" : "\n" + "InformationItem " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_INITIAL_NODE);
		System.out.print(models3.length == 0 ? "" : "\n" + "InitialNode " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_INITIAL_PSEUDO_STATE);
		System.out.print(models3.length == 0 ? "" : "\n" + "InitialPseudoState " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_INITIAL_STATE);
		System.out.print(models3.length == 0 ? "" : "\n" + "InitialState " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_INPUT_PIN);
		System.out.print(models3.length == 0 ? "" : "\n" + "InputPin " + models3.length);

		models3 = projectManager.getProject()
				.toModelElementArray(IModelElementFactory.MODEL_TYPE_INSTANCE_SPECIFICATION);
		System.out.print(models3.length == 0 ? "" : "\n" + "InstanceSpecification " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_INTERACTION);
		System.out.print(models3.length == 0 ? "" : "\n" + "Interaction " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_INTERACTION_ACTOR);
		System.out.print(models3.length == 0 ? "" : "\n" + "InteractionActor " + models3.length);

		models3 = projectManager.getProject()
				.toModelElementArray(IModelElementFactory.MODEL_TYPE_INTERACTION_CONSTRAINT);
		System.out.print(models3.length == 0 ? "" : "\n" + "InteractionConstraint " + models3.length);

		models3 = projectManager.getProject()
				.toModelElementArray(IModelElementFactory.MODEL_TYPE_INTERACTION_DIAGRAM_DURATION_CONSTRAINT);
		System.out.print(models3.length == 0 ? "" : "\n" + "InteractionDiagramDurationConstraint " + models3.length);

		models3 = projectManager.getProject()
				.toModelElementArray(IModelElementFactory.MODEL_TYPE_INTERACTION_LIFE_LINE);
		System.out.print(models3.length == 0 ? "" : "\n" + "InteractionLifeLine " + models3.length);

		models3 = projectManager.getProject()
				.toModelElementArray(IModelElementFactory.MODEL_TYPE_INTERACTION_LIFE_LINE_LINK);
		System.out.print(models3.length == 0 ? "" : "\n" + "InteractionLifeLineLink " + models3.length);

		models3 = projectManager.getProject()
				.toModelElementArray(IModelElementFactory.MODEL_TYPE_INTERACTION_LIFE_LINE_LINK_END);
		System.out.print(models3.length == 0 ? "" : "\n" + "InteractionLifeLineLinkEnd " + models3.length);

		models3 = projectManager.getProject()
				.toModelElementArray(IModelElementFactory.MODEL_TYPE_INTERACTION_OCCURRENCE);
		System.out.print(models3.length == 0 ? "" : "\n" + "InteractionOccurrence " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_INTERACTION_OPERAND);
		System.out.print(models3.length == 0 ? "" : "\n" + "InteractionOperand " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_INTERFACE);
		System.out.print(models3.length == 0 ? "" : "\n" + "Interface " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_INTERNAL_TRANSITION);
		System.out.print(models3.length == 0 ? "" : "\n" + "InternalTransition " + models3.length);

		models3 = projectManager.getProject()
				.toModelElementArray(IModelElementFactory.MODEL_TYPE_INTERRUPTIBLE_ACTIVITY_REGION);
		System.out.print(models3.length == 0 ? "" : "\n" + "InterruptibleActivityRegion " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_INTERVAL_CONSTRAINT);
		System.out.print(models3.length == 0 ? "" : "\n" + "IntervalConstraint " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_INT_VALUE);
		System.out.print(models3.length == 0 ? "" : "\n" + "IntValue " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_JAVA_ANNOTATION);
		System.out.print(models3.length == 0 ? "" : "\n" + "JavaAnnotation " + models3.length);

		models3 = projectManager.getProject()
				.toModelElementArray(IModelElementFactory.MODEL_TYPE_JAVA_ANNOTATION_PROPERTY);
		System.out.print(models3.length == 0 ? "" : "\n" + "JavaAnnotationProperty " + models3.length);

		models3 = projectManager.getProject()
				.toModelElementArray(IModelElementFactory.MODEL_TYPE_JAVA_ATTRIBUTE_CODE_DETAIL);
		System.out.print(models3.length == 0 ? "" : "\n" + "JavaAttributeCodeDetail " + models3.length);

		models3 = projectManager.getProject()
				.toModelElementArray(IModelElementFactory.MODEL_TYPE_JAVA_CLASS_CODE_DETAIL);
		System.out.print(models3.length == 0 ? "" : "\n" + "JavaClassCodeDetail " + models3.length);

		models3 = projectManager.getProject()
				.toModelElementArray(IModelElementFactory.MODEL_TYPE_JAVA_OPERATION_CODE_DETAIL);
		System.out.print(models3.length == 0 ? "" : "\n" + "JavaOperationCodeDetail " + models3.length);

		models3 = projectManager.getProject()
				.toModelElementArray(IModelElementFactory.MODEL_TYPE_JAVA_PARAMETER_CODE_DETAIL);
		System.out.print(models3.length == 0 ? "" : "\n" + "JavaParameterCodeDetail " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_JOIN);
		System.out.print(models3.length == 0 ? "" : "\n" + "Join " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_JOIN_NODE);
		System.out.print(models3.length == 0 ? "" : "\n" + "JoinNode " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_JUNCTION);
		System.out.print(models3.length == 0 ? "" : "\n" + "Junction " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_JUNCTION_POINT);
		System.out.print(models3.length == 0 ? "" : "\n" + "JunctionPoint " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_LIFE_LINE);
		System.out.print(models3.length == 0 ? "" : "\n" + "LifeLine " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_LINK);
		System.out.print(models3.length == 0 ? "" : "\n" + "Link " + models3.length);

		models3 = projectManager.getProject()
				.toModelElementArray(IModelElementFactory.MODEL_TYPE_LINKED_PROJECT_NOT_FOUND_DUMMY_MODEL);
		System.out.print(models3.length == 0 ? "" : "\n" + "LinkedProjectNotFoundDummyModel " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_LOCK);
		System.out.print(models3.length == 0 ? "" : "\n" + "Lock " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_LOCK_CONTAINER);
		System.out.print(models3.length == 0 ? "" : "\n" + "LockContainer " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_LOOP_NODE);
		System.out.print(models3.length == 0 ? "" : "\n" + "LoopNode " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_MERGE_NODE);
		System.out.print(models3.length == 0 ? "" : "\n" + "MergeNode " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_MESSAGE);
		System.out.print(models3.length == 0 ? "" : "\n" + "Message " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_MESSAGE_ACTION);
		System.out.print(models3.length == 0 ? "" : "\n" + "MessageAction " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_MESSAGE_END);
		System.out.print(models3.length == 0 ? "" : "\n" + "MessageEnd " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_MESSAGE_TRIGGER);
		System.out.print(models3.length == 0 ? "" : "\n" + "MessageTrigger " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_MIND_CONNECTOR);
		System.out.print(models3.length == 0 ? "" : "\n" + "MindConnector " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_MIND_LINK);
		System.out.print(models3.length == 0 ? "" : "\n" + "MindLink " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_MIND_MAP);
		System.out.print(models3.length == 0 ? "" : "\n" + "MindMap " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_MIND_MAP_NODE);
		System.out.print(models3.length == 0 ? "" : "\n" + "MindMapNode " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_MODEL);
		System.out.print(models3.length == 0 ? "" : "\n" + "Model " + models3.length);

		models3 = projectManager.getProject()
				.toModelElementArray(IModelElementFactory.MODEL_TYPE_MODEL_ORDER_LIST_CONTAINER);
		System.out.print(models3.length == 0 ? "" : "\n" + "ModelOrderListContainer " + models3.length);

		models3 = projectManager.getProject()
				.toModelElementArray(IModelElementFactory.MODEL_TYPE_MODEL_RELATIONSHIP_CONTAINER);
		System.out.print(models3.length == 0 ? "" : "\n" + "ModelRelationshipContainer " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_MODEL_WITH_OFFSET);
		System.out.print(models3.length == 0 ? "" : "\n" + "ModelWithOffset " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_MULTIPLICITY);
		System.out.print(models3.length == 0 ? "" : "\n" + "Multiplicity " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_NARY);
		System.out.print(models3.length == 0 ? "" : "\n" + "NAR " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_NODE);
		System.out.print(models3.length == 0 ? "" : "\n" + "Node " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_NODE_INSTANCE);
		System.out.print(models3.length == 0 ? "" : "\n" + "NodeInstance " + models3.length);

		models3 = projectManager.getProject()
				.toModelElementArray(IModelElementFactory.MODEL_TYPE_NON_COMPOSITE_STRING_VALUE);
		System.out.print(models3.length == 0 ? "" : "\n" + "NonCompositeStringValue " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_NOTE);
		System.out.print(models3.length == 0 ? "" : "\n" + "NOTE " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_OBJECT);
		System.out.print(models3.length == 0 ? "" : "\n" + "Object " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_OBJECT_FLOW);
		System.out.print(models3.length == 0 ? "" : "\n" + "ObjectFlow " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_OBJECT_NODE);
		System.out.print(models3.length == 0 ? "" : "\n" + "ObjectNode " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_OC_LINE);
		System.out.print(models3.length == 0 ? "" : "\n" + "OCLine " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_OC_UNIT);
		System.out.print(models3.length == 0 ? "" : "\n" + "OCUnit " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_OPERATION);
		System.out.print(models3.length == 0 ? "" : "\n" + "Operation " + models3.length);

		models3 = projectManager.getProject()
				.toModelElementArray(IModelElementFactory.MODEL_TYPE_ORM_ASSOCIATION_DETAIL);
		System.out.print(models3.length == 0 ? "" : "\n" + "ORMAssociationDetail " + models3.length);

		models3 = projectManager.getProject()
				.toModelElementArray(IModelElementFactory.MODEL_TYPE_ORM_ASSOCIATION_END_DETAIL);
		System.out.print(models3.length == 0 ? "" : "\n" + "ORMAssociationEndDetail " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_ORM_ASSO_ORDER_BY);
		System.out.print(models3.length == 0 ? "" : "\n" + "ORMAssoOrderBy " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_ORM_ATTRIBUTE_DETAIL);
		System.out.print(models3.length == 0 ? "" : "\n" + "ORMAttributeDetail " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_ORM_CLASS_DETAIL);
		System.out.print(models3.length == 0 ? "" : "\n" + "ORMClassDetail " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_ORM_CONTAINER);
		System.out.print(models3.length == 0 ? "" : "\n" + "ORMContainer " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_ORM_NAMED_QUERY);
		System.out.print(models3.length == 0 ? "" : "\n" + "ORMNamedQuery " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_ORM_OPERATION_DETAIL);
		System.out.print(models3.length == 0 ? "" : "\n" + "ORMOperationDetail " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_ORM_QUALIFIER);
		System.out.print(models3.length == 0 ? "" : "\n" + "ORMQualifier " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_ORM_TYPE_DEF);
		System.out.print(models3.length == 0 ? "" : "\n" + "ORMTypeDef " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_OUTPUT_PIN);
		System.out.print(models3.length == 0 ? "" : "\n" + "OutputPin " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_OVAL);
		System.out.print(models3.length == 0 ? "" : "\n" + "Oval " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_PACKAGE);
		System.out.print(models3.length == 0 ? "" : "\n" + "Package " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_PARAMETER);
		System.out.print(models3.length == 0 ? "" : "\n" + "Parameter " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_PARAMETER_SET);
		System.out.print(models3.length == 0 ? "" : "\n" + "ParameterSet " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_PERMISSION);
		System.out.print(models3.length == 0 ? "" : "\n" + "Permission " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_PLUGIN_MODEL_ELEMENT);
		System.out.print(models3.length == 0 ? "" : "\n" + "PluginModelElement " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_PLUGIN_RELATIONSHIP);
		System.out.print(models3.length == 0 ? "" : "\n" + "PluginRelationship " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_PM_AUTHOR_CONTAINER);
		System.out.print(models3.length == 0 ? "" : "\n" + "PMAuthorContainer " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_PM_DIFFICULTY);
		System.out.print(models3.length == 0 ? "" : "\n" + "PMDifficulty " + models3.length);

		models3 = projectManager.getProject()
				.toModelElementArray(IModelElementFactory.MODEL_TYPE_PM_DIFFICULTY_CONTAINER);
		System.out.print(models3.length == 0 ? "" : "\n" + "PMDifficultyContainer " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_PM_DISCIPLINE);
		System.out.print(models3.length == 0 ? "" : "\n" + "PMDiscipline " + models3.length);

		models3 = projectManager.getProject()
				.toModelElementArray(IModelElementFactory.MODEL_TYPE_PM_DISCIPLINE_CONTAINER);
		System.out.print(models3.length == 0 ? "" : "\n" + "PMDisciplineContainer " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_PM_ITERATION);
		System.out.print(models3.length == 0 ? "" : "\n" + "PMIteration " + models3.length);

		models3 = projectManager.getProject()
				.toModelElementArray(IModelElementFactory.MODEL_TYPE_PM_ITERATION_CONTAINER);
		System.out.print(models3.length == 0 ? "" : "\n" + "PMIterationContainer " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_PM_PHASE);
		System.out.print(models3.length == 0 ? "" : "\n" + "PMPhase " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_PM_PHASE_CONTAINER);
		System.out.print(models3.length == 0 ? "" : "\n" + "PMPhaseContainer " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_PM_PRIORITY);
		System.out.print(models3.length == 0 ? "" : "\n" + "PMPriority " + models3.length);

		models3 = projectManager.getProject()
				.toModelElementArray(IModelElementFactory.MODEL_TYPE_PM_PRIORITY_CONTAINER);
		System.out.print(models3.length == 0 ? "" : "\n" + "PMPriorityContainer " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_PM_PROCESS);
		System.out.print(models3.length == 0 ? "" : "\n" + "PMProcess " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_PM_PROCESS_LINK);
		System.out.print(models3.length == 0 ? "" : "\n" + "PMProcessLink " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_PM_RECEIVE);
		System.out.print(models3.length == 0 ? "" : "\n" + "PMReceive " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_PM_SEND);
		System.out.print(models3.length == 0 ? "" : "\n" + "PMSend " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_PM_STATUS);
		System.out.print(models3.length == 0 ? "" : "\n" + "PMStatus " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_PM_STATUS_CONTAINER);
		System.out.print(models3.length == 0 ? "" : "\n" + "PMStatusContainer " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_PM_VERSION);
		System.out.print(models3.length == 0 ? "" : "\n" + "PMVersion " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_PM_VERSION_CONTAINER);
		System.out.print(models3.length == 0 ? "" : "\n" + "PMVersionContainer " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_POLYGON);
		System.out.print(models3.length == 0 ? "" : "\n" + "Polygon " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_PORT);
		System.out.print(models3.length == 0 ? "" : "\n" + "Port " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_PROCEDURE);
		System.out.print(models3.length == 0 ? "" : "\n" + "Procedure " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_PROCEDURE_CONTAINER);
		System.out.print(models3.length == 0 ? "" : "\n" + "ProcedureContainer " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_PROCEDURE_PARAMETER);
		System.out.print(models3.length == 0 ? "" : "\n" + "ProcedureParameter " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_PROCEDURE_RESULT_SET);
		System.out.print(models3.length == 0 ? "" : "\n" + "ProcedureResultSet " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_PROFILE);
		System.out.print(models3.length == 0 ? "" : "\n" + "Profile " + models3.length);

		models3 = projectManager.getProject()
				.toModelElementArray(IModelElementFactory.MODEL_TYPE_PROJECT_DEFAULT_LINE_MODEL);
		System.out.print(models3.length == 0 ? "" : "\n" + "ProjectDefaultLineModel " + models3.length);

		models3 = projectManager.getProject()
				.toModelElementArray(IModelElementFactory.MODEL_TYPE_PROJECT_ELEMENT_FONT_MODEL);
		System.out.print(models3.length == 0 ? "" : "\n" + "ProjectElementFontModel " + models3.length);

		models3 = projectManager.getProject()
				.toModelElementArray(IModelElementFactory.MODEL_TYPE_PROJECT_FILL_COLOR_MODEL);
		System.out.print(models3.length == 0 ? "" : "\n" + "ProjectFillColorModel " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_PROJECT_FORMAT);
		System.out.print(models3.length == 0 ? "" : "\n" + "ProjectFormat " + models3.length);

		models3 = projectManager.getProject()
				.toModelElementArray(IModelElementFactory.MODEL_TYPE_PROJECT_SHAPE_DISPLAY_IMAGE);
		System.out.print(models3.length == 0 ? "" : "\n" + "ProjectShapeDisplayImage " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_QUALIFIER);
		System.out.print(models3.length == 0 ? "" : "\n" + "Qualifier " + models3.length);

		models3 = projectManager.getProject()
				.toModelElementArray(IModelElementFactory.MODEL_TYPE_RDC_DIAGRAM_ELEMENT_BOUNDS);
		System.out.print(models3.length == 0 ? "" : "\n" + "RDCDiagramElementBounds " + models3.length);

		models3 = projectManager.getProject()
				.toModelElementArray(IModelElementFactory.MODEL_TYPE_RDC_FREE_STYLE_HTML_TEMPLATE);
		System.out.print(models3.length == 0 ? "" : "\n" + "RDCFreeStyleHtmlTemplate " + models3.length);

		models3 = projectManager.getProject()
				.toModelElementArray(IModelElementFactory.MODEL_TYPE_RDC_FREE_STYLE_PAGE_TEMPLATE);
		System.out.print(models3.length == 0 ? "" : "\n" + "RDCFreeStylePageTemplate " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_RDC_GROUP_ITEM);
		System.out.print(models3.length == 0 ? "" : "\n" + "RDCGroupItem " + models3.length);

		models3 = projectManager.getProject()
				.toModelElementArray(IModelElementFactory.MODEL_TYPE_RDC_HEADING_RESET_BLOCK_END);
		System.out.print(models3.length == 0 ? "" : "\n" + "RDCHeadingResetBlockEnd " + models3.length);

		models3 = projectManager.getProject()
				.toModelElementArray(IModelElementFactory.MODEL_TYPE_RDC_HEADING_RESET_BLOCK_START);
		System.out.print(models3.length == 0 ? "" : "\n" + "RDCHeadingResetBlockStart " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_RDC_IMAGE);
		System.out.print(models3.length == 0 ? "" : "\n" + "RDCImage " + models3.length);

		models3 = projectManager.getProject()
				.toModelElementArray(IModelElementFactory.MODEL_TYPE_RDC_NUMBERING_DETAILS);
		System.out.print(models3.length == 0 ? "" : "\n" + "RDCNumberingDetails " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_RDC_PAGE_BREAK);
		System.out.print(models3.length == 0 ? "" : "\n" + "RDCPageBreak " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_RDC_PARAGRAPH_BREAK);
		System.out.print(models3.length == 0 ? "" : "\n" + "RDCParagraphBreak " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_RDC_SPACING);
		System.out.print(models3.length == 0 ? "" : "\n" + "RDCSpacing " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_RDC_TABLE);
		System.out.print(models3.length == 0 ? "" : "\n" + "RDCTable " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_RDC_TABLE_CELL);
		System.out.print(models3.length == 0 ? "" : "\n" + "RDCTableCell " + models3.length);

		models3 = projectManager.getProject()
				.toModelElementArray(IModelElementFactory.MODEL_TYPE_RDC_TABLE_OF_CONTENTS_TEMPLATE);
		System.out.print(models3.length == 0 ? "" : "\n" + "RDCTableOfContentsTemplate " + models3.length);

		models3 = projectManager.getProject()
				.toModelElementArray(IModelElementFactory.MODEL_TYPE_RDC_TABLE_OF_CONTENT_ROW);
		System.out.print(models3.length == 0 ? "" : "\n" + "RDCTableOfContentRow " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_RDC_TABLE_ROW);
		System.out.print(models3.length == 0 ? "" : "\n" + "RDCTableRow " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_RDC_TEXT);
		System.out.print(models3.length == 0 ? "" : "\n" + "RDCText " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_RDOO_TEMPLATE);
		System.out.print(models3.length == 0 ? "" : "\n" + "RDOOTemplate " + models3.length);

		models3 = projectManager.getProject()
				.toModelElementArray(IModelElementFactory.MODEL_TYPE_RD_DIAGRAM_ELEMENT_BOUNDS);
		System.out.print(models3.length == 0 ? "" : "\n" + "RDDiagramElementBounds " + models3.length);

		models3 = projectManager.getProject()
				.toModelElementArray(IModelElementFactory.MODEL_TYPE_RD_FREE_STYLE_HTML_TEMPLATE);
		System.out.print(models3.length == 0 ? "" : "\n" + "RDFreeStyleHtmlTemplate " + models3.length);

		models3 = projectManager.getProject()
				.toModelElementArray(IModelElementFactory.MODEL_TYPE_RD_FREE_STYLE_PAGE_TEMPLATE);
		System.out.print(models3.length == 0 ? "" : "\n" + "RDFreeStylePageTemplate " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_RD_GROUP_ITEM);
		System.out.print(models3.length == 0 ? "" : "\n" + "RDGroupItem " + models3.length);

		models3 = projectManager.getProject()
				.toModelElementArray(IModelElementFactory.MODEL_TYPE_RD_HEADING_RESET_BLOCK_END);
		System.out.print(models3.length == 0 ? "" : "\n" + "RDHeadingResetBlockEnd " + models3.length);

		models3 = projectManager.getProject()
				.toModelElementArray(IModelElementFactory.MODEL_TYPE_RD_HEADING_RESET_BLOCK_START);
		System.out.print(models3.length == 0 ? "" : "\n" + "RDHeadingResetBlockStart " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_RD_HTML_TEMPLATE);
		System.out.print(models3.length == 0 ? "" : "\n" + "RDHtmlTemplate " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_RD_IMAGE);
		System.out.print(models3.length == 0 ? "" : "\n" + "RDImage " + models3.length);

		models3 = projectManager.getProject()
				.toModelElementArray(IModelElementFactory.MODEL_TYPE_RD_IMAGE_RENDERING_VALUES);
		System.out.print(models3.length == 0 ? "" : "\n" + "RDImageRenderingValues " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_RD_ITEM_MARGIN);
		System.out.print(models3.length == 0 ? "" : "\n" + "RDItemMargin " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_RD_LOOP_SETTING);
		System.out.print(models3.length == 0 ? "" : "\n" + "RDLoopSetting " + models3.length);

		models3 = projectManager.getProject()
				.toModelElementArray(IModelElementFactory.MODEL_TYPE_RD_LOOP_SETTING_MODEL_TYPE_TEMPLATE);
		System.out.print(models3.length == 0 ? "" : "\n" + "RDLoopSettingModelTypeTemplate " + models3.length);

		models3 = projectManager.getProject()
				.toModelElementArray(IModelElementFactory.MODEL_TYPE_RD_LOOP_TEMPLATE_SETTING);
		System.out.print(models3.length == 0 ? "" : "\n" + "RDLoopTemplateSetting " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_RD_NUMBERING_DETAILS);
		System.out.print(models3.length == 0 ? "" : "\n" + "RDNumberingDetails " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_RD_NUMBERING_SETTING);
		System.out.print(models3.length == 0 ? "" : "\n" + "RDNumberingSetting " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_RD_PAGE_BREAK);
		System.out.print(models3.length == 0 ? "" : "\n" + "RDPageBreak " + models3.length);

		models3 = projectManager.getProject()
				.toModelElementArray(IModelElementFactory.MODEL_TYPE_RD_PAGE_BREAK_TEMPLATE);
		System.out.print(models3.length == 0 ? "" : "\n" + "RDPageBreakTemplate " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_RD_PARAGRAPH_BREAK);
		System.out.print(models3.length == 0 ? "" : "\n" + "RDParagraphBreak " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_RD_REVISION_LOG_CELL);
		System.out.print(models3.length == 0 ? "" : "\n" + "RDRevisionLogCell " + models3.length);

		models3 = projectManager.getProject()
				.toModelElementArray(IModelElementFactory.MODEL_TYPE_RD_REVISION_LOG_COLUMN);
		System.out.print(models3.length == 0 ? "" : "\n" + "RDRevisionLogColumn " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_RD_REVISION_LOG_ROW);
		System.out.print(models3.length == 0 ? "" : "\n" + "RDRevisionLogRow " + models3.length);

		models3 = projectManager.getProject()
				.toModelElementArray(IModelElementFactory.MODEL_TYPE_RD_REVISION_LOG_TEMPLATE);
		System.out.print(models3.length == 0 ? "" : "\n" + "RDRevisionLogTemplate " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_RD_SECTION_TEMPLATE);
		System.out.print(models3.length == 0 ? "" : "\n" + "RDSectionTemplate " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_RD_SPACING);
		System.out.print(models3.length == 0 ? "" : "\n" + "RDSpacing " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_RD_STYLE);
		System.out.print(models3.length == 0 ? "" : "\n" + "RDStyle " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_RD_STYLE_REPOSITORY);
		System.out.print(models3.length == 0 ? "" : "\n" + "RDStyleRepository " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_RD_TABLE);
		System.out.print(models3.length == 0 ? "" : "\n" + "RDTable " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_RD_TABLE_BORDER);
		System.out.print(models3.length == 0 ? "" : "\n" + "RDTableBorder " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_RD_TABLE_CELL);
		System.out.print(models3.length == 0 ? "" : "\n" + "RDTableCell " + models3.length);

		models3 = projectManager.getProject()
				.toModelElementArray(IModelElementFactory.MODEL_TYPE_RD_TABLE_OF_CONTENTS_TEMPLATE);
		System.out.print(models3.length == 0 ? "" : "\n" + "RDTableOfContentsTemplate " + models3.length);

		models3 = projectManager.getProject()
				.toModelElementArray(IModelElementFactory.MODEL_TYPE_RD_TABLE_OF_CONTENT_ROW);
		System.out.print(models3.length == 0 ? "" : "\n" + "RDTableOfContentRow " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_RD_TABLE_PROPERTY);
		System.out.print(models3.length == 0 ? "" : "\n" + "RDTableProperty " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_RD_TABLE_ROW);
		System.out.print(models3.length == 0 ? "" : "\n" + "RDTableRow " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_RD_TABLE_STYLE);
		System.out.print(models3.length == 0 ? "" : "\n" + "RDTableStyle " + models3.length);

		models3 = projectManager.getProject()
				.toModelElementArray(IModelElementFactory.MODEL_TYPE_RD_TABLE_STYLE_PROPERTY);
		System.out.print(models3.length == 0 ? "" : "\n" + "RDTableStyleProperty " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_RD_TEMPLATE_VARIABLE);
		System.out.print(models3.length == 0 ? "" : "\n" + "RDTemplateVariable " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_RD_TEXT);
		System.out.print(models3.length == 0 ? "" : "\n" + "RDText " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_REALIZATION);
		System.out.print(models3.length == 0 ? "" : "\n" + "Realization " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_RECTANGLE);
		System.out.print(models3.length == 0 ? "" : "\n" + "Rectangle " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_REFERENCE);
		System.out.print(models3.length == 0 ? "" : "\n" + "Reference " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_REFERENCE_CONTAINER);
		System.out.print(models3.length == 0 ? "" : "\n" + "ReferenceContainer " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_REGION);
		System.out.print(models3.length == 0 ? "" : "\n" + "Region " + models3.length);

		models3 = projectManager.getProject()
				.toModelElementArray(IModelElementFactory.MODEL_TYPE_REPORT_DIAGRAM_DETAILS);
		System.out.print(models3.length == 0 ? "" : "\n" + "ReportDiagramDetails " + models3.length);

		models3 = projectManager.getProject()
				.toModelElementArray(IModelElementFactory.MODEL_TYPE_REPORT_DIAGRAM_DETAILS_CONTAINER);
		System.out.print(models3.length == 0 ? "" : "\n" + "ReportDiagramDetailsContainer " + models3.length);

		models3 = projectManager.getProject()
				.toModelElementArray(IModelElementFactory.MODEL_TYPE_REPORT_DIAGRAM_OPTION);
		System.out.print(models3.length == 0 ? "" : "\n" + "ReportDiagramOption " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_REPORT_DOC_INFO);
		System.out.print(models3.length == 0 ? "" : "\n" + "ReportDocInfo " + models3.length);

		models3 = projectManager.getProject()
				.toModelElementArray(IModelElementFactory.MODEL_TYPE_REPORT_HEADER_FOOTER_BLOCK);
		System.out.print(models3.length == 0 ? "" : "\n" + "ReportHeaderFooterBlock " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_REPORT_PAGE_INFO);
		System.out.print(models3.length == 0 ? "" : "\n" + "ReportPageInfo " + models3.length);

		models3 = projectManager.getProject()
				.toModelElementArray(IModelElementFactory.MODEL_TYPE_REPORT_WATERMARK_OPTION);
		System.out.print(models3.length == 0 ? "" : "\n" + "ReportWatermarkOption " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_REQUIREMENT);
		System.out.print(models3.length == 0 ? "" : "\n" + "Requirement " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_REQUIREMENT_DERIVE);
		System.out.print(models3.length == 0 ? "" : "\n" + "RequirementDerive " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_REQUIREMENT_MODEL);
		System.out.print(models3.length == 0 ? "" : "\n" + "RequirementModel " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_REST_RESOURCE);
		System.out.print(models3.length == 0 ? "" : "\n" + "RestResource " + models3.length);

		models3 = projectManager.getProject()
				.toModelElementArray(IModelElementFactory.MODEL_TYPE_REST_RESOURCE_PARAMETER);
		System.out.print(models3.length == 0 ? "" : "\n" + "RestResourceParameter " + models3.length);

		models3 = projectManager.getProject()
				.toModelElementArray(IModelElementFactory.MODEL_TYPE_REST_RESOURCE_REQUEST_BODY);
		System.out.print(models3.length == 0 ? "" : "\n" + "RestResourceRequestBody " + models3.length);

		models3 = projectManager.getProject()
				.toModelElementArray(IModelElementFactory.MODEL_TYPE_REST_RESOURCE_RESPONSE_BODY);
		System.out.print(models3.length == 0 ? "" : "\n" + "RestResourceResponseBody " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_RQ_REFINE);
		System.out.print(models3.length == 0 ? "" : "\n" + "RQRefine " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_RQ_TRACE);
		System.out.print(models3.length == 0 ? "" : "\n" + "RQTrace " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_SATISFY);
		System.out.print(models3.length == 0 ? "" : "\n" + "Satisfy " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_SEND_SIGNAL_ACTION);
		System.out.print(models3.length == 0 ? "" : "\n" + "SendSignalAction " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_SEQUENCE_NODE);
		System.out.print(models3.length == 0 ? "" : "\n" + "SequenceNode " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_SHALLOW_HISTORY);
		System.out.print(models3.length == 0 ? "" : "\n" + "ShallowHistory " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_SHAPE_EDITOR_SHAPE);
		System.out.print(models3.length == 0 ? "" : "\n" + "ShapeEditorShape " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_SHELL);
		System.out.print(models3.length == 0 ? "" : "\n" + "Shell " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_SHELL_CONTAINER);
		System.out.print(models3.length == 0 ? "" : "\n" + "ShellContainer " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_SIGNAL);
		System.out.print(models3.length == 0 ? "" : "\n" + "Signal " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_SIGNAL_RECEIPT);
		System.out.print(models3.length == 0 ? "" : "\n" + "SignalReceipt " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_SIGNAL_SENDING);
		System.out.print(models3.length == 0 ? "" : "\n" + "SignalSending " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_SIGNAL_TRIGGER);
		System.out.print(models3.length == 0 ? "" : "\n" + "SignalTrigger " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_SLOT);
		System.out.print(models3.length == 0 ? "" : "\n" + "Slot " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_SORT_COLUMN_INFO);
		System.out.print(models3.length == 0 ? "" : "\n" + "SortColumnInfo " + models3.length);

		models3 = projectManager.getProject()
				.toModelElementArray(IModelElementFactory.MODEL_TYPE_SORT_COLUMN_INFO_CONTAINER);
		System.out.print(models3.length == 0 ? "" : "\n" + "SortColumnInfoContainer " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_STATE);
		System.out.print(models3.length == 0 ? "" : "\n" + "State " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_STATE2);
		System.out.print(models3.length == 0 ? "" : "\n" + "State2 " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_STATE_CONDITION);
		System.out.print(models3.length == 0 ? "" : "\n" + "StateCondition " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_STATE_INVARIANT);
		System.out.print(models3.length == 0 ? "" : "\n" + "StateInvariant " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_STATE_MACHINE);
		System.out.print(models3.length == 0 ? "" : "\n" + "StateMachine " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_STEP);
		System.out.print(models3.length == 0 ? "" : "\n" + "Step " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_STEP_CONTAINER);
		System.out.print(models3.length == 0 ? "" : "\n" + "StepContainer " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_STEREOTYPE);
		System.out.print(models3.length == 0 ? "" : "\n" + "Stereotype " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_STRING_VALUE);
		System.out.print(models3.length == 0 ? "" : "\n" + "StringValue " + models3.length);

		models3 = projectManager.getProject()
				.toModelElementArray(IModelElementFactory.MODEL_TYPE_STRUCTURED_ACTIVITY_NODE);
		System.out.print(models3.length == 0 ? "" : "\n" + "StructuredActivityNode " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_STRUCTURED_CLASS);
		System.out.print(models3.length == 0 ? "" : "\n" + "StructuredClass " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_STUB_STATE);
		System.out.print(models3.length == 0 ? "" : "\n" + "StubState " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_STYLE);
		System.out.print(models3.length == 0 ? "" : "\n" + "Style " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_STYLE_SET_CONTAINER);
		System.out.print(models3.length == 0 ? "" : "\n" + "StyleSetContainer " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_SUBMACHINE_STATE);
		System.out.print(models3.length == 0 ? "" : "\n" + "SubmachineState " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_SUBMACHINE_STATE2);
		System.out.print(models3.length == 0 ? "" : "\n" + "SubmachineState2 " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_SUBSYSTEM);
		System.out.print(models3.length == 0 ? "" : "\n" + "Subsystem " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_SUB_ACTIVITY);
		System.out.print(models3.length == 0 ? "" : "\n" + "SubActivity " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_SUB_CONVERSATION);
		System.out.print(models3.length == 0 ? "" : "\n" + "SubConversation " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_SUB_LEVEL_IN_FLOW);
		System.out.print(models3.length == 0 ? "" : "\n" + "SubLevelInFlow " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_SUB_LEVEL_OUT_FLOW);
		System.out.print(models3.length == 0 ? "" : "\n" + "SubLevelOutFlow " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_SWIM_LANE);
		System.out.print(models3.length == 0 ? "" : "\n" + "SwimLane " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_SYNCHRONIZATIONBAR);
		System.out.print(models3.length == 0 ? "" : "\n" + "Synchronizationbar " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_SYNCH_STATE);
		System.out.print(models3.length == 0 ? "" : "\n" + "SynchState " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_SYSTEM);
		System.out.print(models3.length == 0 ? "" : "\n" + "System " + models3.length);

		models3 = projectManager.getProject()
				.toModelElementArray(IModelElementFactory.MODEL_TYPE_SYS_ML_ACTIVITY_BLOCK);
		System.out.print(models3.length == 0 ? "" : "\n" + "SysMLActivityBlock " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_SYS_ML_ALLOCATE);
		System.out.print(models3.length == 0 ? "" : "\n" + "SysMLAllocate " + models3.length);

		models3 = projectManager.getProject()
				.toModelElementArray(IModelElementFactory.MODEL_TYPE_SYS_ML_ASSOCIATION_BLOCK);
		System.out.print(models3.length == 0 ? "" : "\n" + "SysMLAssociationBlock " + models3.length);

		models3 = projectManager.getProject()
				.toModelElementArray(IModelElementFactory.MODEL_TYPE_SYS_ML_BINDING_CONNECTOR);
		System.out.print(models3.length == 0 ? "" : "\n" + "SysMLBindingConnector " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_SYS_ML_BLOCK);
		System.out.print(models3.length == 0 ? "" : "\n" + "SysMLBlock " + models3.length);

		models3 = projectManager.getProject()
				.toModelElementArray(IModelElementFactory.MODEL_TYPE_SYS_ML_CONSTRAINT_BLOCK);
		System.out.print(models3.length == 0 ? "" : "\n" + "SysMLConstraintBlock " + models3.length);

		models3 = projectManager.getProject()
				.toModelElementArray(IModelElementFactory.MODEL_TYPE_SYS_ML_CONSTRAINT_PROPERTY);
		System.out.print(models3.length == 0 ? "" : "\n" + "SysMLConstraintProperty " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_SYS_ML_COPY);
		System.out.print(models3.length == 0 ? "" : "\n" + "SysMLCopy " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_SYS_ML_FLOW_PORT);
		System.out.print(models3.length == 0 ? "" : "\n" + "SysMLFlowPort " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_SYS_ML_FLOW_PROPERTY);
		System.out.print(models3.length == 0 ? "" : "\n" + "SysMLFlowProperty " + models3.length);

		models3 = projectManager.getProject()
				.toModelElementArray(IModelElementFactory.MODEL_TYPE_SYS_ML_FLOW_RATE_CONTINUOUS);
		System.out.print(models3.length == 0 ? "" : "\n" + "SysMLFlowRateContinuous " + models3.length);

		models3 = projectManager.getProject()
				.toModelElementArray(IModelElementFactory.MODEL_TYPE_SYS_ML_FLOW_RATE_DISCRETE);
		System.out.print(models3.length == 0 ? "" : "\n" + "SysMLFlowRateDiscrete " + models3.length);

		models3 = projectManager.getProject()
				.toModelElementArray(IModelElementFactory.MODEL_TYPE_SYS_ML_FLOW_SPECIFICATION);
		System.out.print(models3.length == 0 ? "" : "\n" + "SysMLFlowSpecification " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_SYS_ML_INITIAL_VALUE);
		System.out.print(models3.length == 0 ? "" : "\n" + "SysMLInitialValue " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_SYS_ML_ITEM_FLOW);
		System.out.print(models3.length == 0 ? "" : "\n" + "SysMLItemFlow " + models3.length);

		models3 = projectManager.getProject()
				.toModelElementArray(IModelElementFactory.MODEL_TYPE_SYS_ML_PARTICIPANT_PROPERTY);
		System.out.print(models3.length == 0 ? "" : "\n" + "SysMLParticipantProperty " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_SYS_ML_QUANTITY_KIND);
		System.out.print(models3.length == 0 ? "" : "\n" + "SysMLQuantityKind " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_SYS_ML_SIGNAL);
		System.out.print(models3.length == 0 ? "" : "\n" + "SysMLSignal " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_SYS_ML_UNIT);
		System.out.print(models3.length == 0 ? "" : "\n" + "SysMLUnit " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_SYS_ML_VALUE_TYPE);
		System.out.print(models3.length == 0 ? "" : "\n" + "SysMLValueType " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_TABLE);
		System.out.print(models3.length == 0 ? "" : "\n" + "Table " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_TAGGED_VALUE);
		System.out.print(models3.length == 0 ? "" : "\n" + "TaggedValue " + models3.length);

		models3 = projectManager.getProject()
				.toModelElementArray(IModelElementFactory.MODEL_TYPE_TAGGED_VALUE_CONTAINER);
		System.out.print(models3.length == 0 ? "" : "\n" + "TaggedValueContainer " + models3.length);

		models3 = projectManager.getProject()
				.toModelElementArray(IModelElementFactory.MODEL_TYPE_TAGGED_VALUE_DEFINITION);
		System.out.print(models3.length == 0 ? "" : "\n" + "TaggedValueDefinition " + models3.length);

		models3 = projectManager.getProject()
				.toModelElementArray(IModelElementFactory.MODEL_TYPE_TAGGED_VALUE_DEFINITION_CONTAINER);
		System.out.print(models3.length == 0 ? "" : "\n" + "TaggedValueDefinitionContainer " + models3.length);

		models3 = projectManager.getProject()
				.toModelElementArray(IModelElementFactory.MODEL_TYPE_TAGGED_VALUE_ENUMERATION);
		System.out.print(models3.length == 0 ? "" : "\n" + "TaggedValueEnumeration " + models3.length);

		models3 = projectManager.getProject()
				.toModelElementArray(IModelElementFactory.MODEL_TYPE_TAGGED_VALUE_ENUMERATION_CONTAINER);
		System.out.print(models3.length == 0 ? "" : "\n" + "TaggedValueEnumerationContainer " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_TASIFIER_MEMBER);
		System.out.print(models3.length == 0 ? "" : "\n" + "TasifierMember " + models3.length);

		models3 = projectManager.getProject()
				.toModelElementArray(IModelElementFactory.MODEL_TYPE_TASIFIER_MEMBER_CONTAINER);
		System.out.print(models3.length == 0 ? "" : "\n" + "TasifierMemberContainer " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_TEMPLATE_PARAMETER);
		System.out.print(models3.length == 0 ? "" : "\n" + "TemplateParameter " + models3.length);

		models3 = projectManager.getProject()
				.toModelElementArray(IModelElementFactory.MODEL_TYPE_TEMPLATE_TYPE_BIND_DETAILS);
		System.out.print(models3.length == 0 ? "" : "\n" + "TemplateTypeBindDetails " + models3.length);

		models3 = projectManager.getProject()
				.toModelElementArray(IModelElementFactory.MODEL_TYPE_TEMPLATE_TYPE_BIND_INFO);
		System.out.print(models3.length == 0 ? "" : "\n" + "TemplateTypeBindInfo " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_TERM);
		System.out.print(models3.length == 0 ? "" : "\n" + "Term " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_TERMINATE);
		System.out.print(models3.length == 0 ? "" : "\n" + "Terminate " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_TESTING_PROCEDURE);
		System.out.print(models3.length == 0 ? "" : "\n" + "TestingProcedure " + models3.length);

		models3 = projectManager.getProject()
				.toModelElementArray(IModelElementFactory.MODEL_TYPE_TESTING_PROCEDURE_CONTAINER);
		System.out.print(models3.length == 0 ? "" : "\n" + "TestingProcedureContainer " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_TEST_CASE);
		System.out.print(models3.length == 0 ? "" : "\n" + "TestCase " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_TEST_CASE_STEP);
		System.out.print(models3.length == 0 ? "" : "\n" + "TestCaseStep " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_TIME_CONSTRAINT);
		System.out.print(models3.length == 0 ? "" : "\n" + "TimeConstraint " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_TIME_INSTANCE);
		System.out.print(models3.length == 0 ? "" : "\n" + "TimeInstance " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_TIME_MESSAGE);
		System.out.print(models3.length == 0 ? "" : "\n" + "TimeMessage " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_TIME_TRIGGER);
		System.out.print(models3.length == 0 ? "" : "\n" + "TimeTrigger " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_TIME_UNIT);
		System.out.print(models3.length == 0 ? "" : "\n" + "TimeUnit " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_TIMING_FRAME);
		System.out.print(models3.length == 0 ? "" : "\n" + "TimingFrame " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_TRANSITION);
		System.out.print(models3.length == 0 ? "" : "\n" + "Transition " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_TRANSITION2);
		System.out.print(models3.length == 0 ? "" : "\n" + "Transition2 " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_TYPE_INFO);
		System.out.print(models3.length == 0 ? "" : "\n" + "TypeInfo " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_UE_XCELER_INFO);
		System.out.print(models3.length == 0 ? "" : "\n" + "UeXcelerInfo " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_UI_BUTTON);
		System.out.print(models3.length == 0 ? "" : "\n" + "UIButton " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_UI_CHECK_BOX);
		System.out.print(models3.length == 0 ? "" : "\n" + "UICheckBox " + models3.length);

		models3 = projectManager.getProject()
				.toModelElementArray(IModelElementFactory.MODEL_TYPE_UI_CHECK_BOX_MENU_ITEM);
		System.out.print(models3.length == 0 ? "" : "\n" + "UICheckBoxMenuItem " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_UI_COMBO_BOX);
		System.out.print(models3.length == 0 ? "" : "\n" + "UIComboBox " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_UI_FRAME);
		System.out.print(models3.length == 0 ? "" : "\n" + "UIFrame " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_UI_LABEL);
		System.out.print(models3.length == 0 ? "" : "\n" + "UILabel " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_UI_LIST);
		System.out.print(models3.length == 0 ? "" : "\n" + "UIList " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_UI_MENU);
		System.out.print(models3.length == 0 ? "" : "\n" + "UIMenu " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_UI_MENUBAR);
		System.out.print(models3.length == 0 ? "" : "\n" + "UIMenubar " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_UI_MENU_ITEM);
		System.out.print(models3.length == 0 ? "" : "\n" + "UIMenuItem " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_UI_MENU_SEPARATOR);
		System.out.print(models3.length == 0 ? "" : "\n" + "UIMenuSeparator " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_UI_PANEL);
		System.out.print(models3.length == 0 ? "" : "\n" + "UIPanel " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_UI_PASSWORD_FIELD);
		System.out.print(models3.length == 0 ? "" : "\n" + "UIPasswordField " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_UI_PROGRESS_BAR);
		System.out.print(models3.length == 0 ? "" : "\n" + "UIProgressBar " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_UI_RADIO_BUTTON);
		System.out.print(models3.length == 0 ? "" : "\n" + "UIRadioButton " + models3.length);

		models3 = projectManager.getProject()
				.toModelElementArray(IModelElementFactory.MODEL_TYPE_UI_RADIO_BUTTON_MENU_ITEM);
		System.out.print(models3.length == 0 ? "" : "\n" + "UIRadioButtonMenuItem " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_UI_SCROLL_BAR);
		System.out.print(models3.length == 0 ? "" : "\n" + "UIScrollBar " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_UI_SEPARATOR);
		System.out.print(models3.length == 0 ? "" : "\n" + "UISeparator " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_UI_SLIDER);
		System.out.print(models3.length == 0 ? "" : "\n" + "UISlider " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_UI_SPINNER);
		System.out.print(models3.length == 0 ? "" : "\n" + "UISpinner " + models3.length);

		models3 = projectManager.getProject()
				.toModelElementArray(IModelElementFactory.MODEL_TYPE_UI_SPINNER_DATE_MODEL);
		System.out.print(models3.length == 0 ? "" : "\n" + "UISpinnerDateModel " + models3.length);

		models3 = projectManager.getProject()
				.toModelElementArray(IModelElementFactory.MODEL_TYPE_UI_SPINNER_LIST_MODEL);
		System.out.print(models3.length == 0 ? "" : "\n" + "UISpinnerListModel " + models3.length);

		models3 = projectManager.getProject()
				.toModelElementArray(IModelElementFactory.MODEL_TYPE_UI_SPINNER_NUMBER_MODEL);
		System.out.print(models3.length == 0 ? "" : "\n" + "UISpinnerNumberModel " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_UI_TABBED_HEADER);
		System.out.print(models3.length == 0 ? "" : "\n" + "UITabbedHeader " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_UI_TABLE);
		System.out.print(models3.length == 0 ? "" : "\n" + "UITable " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_UI_TABLE_COLUMN);
		System.out.print(models3.length == 0 ? "" : "\n" + "UITableColumn " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_UI_TEXT_AREA);
		System.out.print(models3.length == 0 ? "" : "\n" + "UITextArea " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_UI_TEXT_FIELD);
		System.out.print(models3.length == 0 ? "" : "\n" + "UITextField " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_UI_TOGGLE_BUTTON);
		System.out.print(models3.length == 0 ? "" : "\n" + "UIToggleButton " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_UI_TREE);
		System.out.print(models3.length == 0 ? "" : "\n" + "UITree " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_UI_TREE_NODE);
		System.out.print(models3.length == 0 ? "" : "\n" + "UITreeNode " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_UI_WEB_BUTTON_INPUT);
		System.out.print(models3.length == 0 ? "" : "\n" + "UIWebButtonInput " + models3.length);

		models3 = projectManager.getProject()
				.toModelElementArray(IModelElementFactory.MODEL_TYPE_UI_WEB_CHECK_BOX_INPUT);
		System.out.print(models3.length == 0 ? "" : "\n" + "UIWebCheckBoxInput " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_UI_WEB_COMBO_BOX);
		System.out.print(models3.length == 0 ? "" : "\n" + "UIWebComboBox " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_UI_WEB_FILE_INPUT);
		System.out.print(models3.length == 0 ? "" : "\n" + "UIWebFileInput " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_UI_WEB_INPUT);
		System.out.print(models3.length == 0 ? "" : "\n" + "UIWebInput " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_UI_WEB_LABEL);
		System.out.print(models3.length == 0 ? "" : "\n" + "UIWebLabel " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_UI_WEB_LIST);
		System.out.print(models3.length == 0 ? "" : "\n" + "UIWebList " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_UI_WEB_OPTION);
		System.out.print(models3.length == 0 ? "" : "\n" + "UIWebOption " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_UI_WEB_PANEL);
		System.out.print(models3.length == 0 ? "" : "\n" + "UIWebPanel " + models3.length);

		models3 = projectManager.getProject()
				.toModelElementArray(IModelElementFactory.MODEL_TYPE_UI_WEB_PASSWORD_INPUT);
		System.out.print(models3.length == 0 ? "" : "\n" + "UIWebPasswordInput " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_UI_WEB_RADIO_INPUT);
		System.out.print(models3.length == 0 ? "" : "\n" + "UIWebRadioInput " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_UI_WEB_RESET_INPUT);
		System.out.print(models3.length == 0 ? "" : "\n" + "UIWebResetInput " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_UI_WEB_SUBMIT_INPUT);
		System.out.print(models3.length == 0 ? "" : "\n" + "UIWebSubmitInput " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_UI_WEB_TEXT_AREA);
		System.out.print(models3.length == 0 ? "" : "\n" + "UIWebTextArea " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_UI_WEB_TEXT_INPUT);
		System.out.print(models3.length == 0 ? "" : "\n" + "UIWebTextInput " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_UNIQUE_CONSTRAINT);
		System.out.print(models3.length == 0 ? "" : "\n" + "UniqueConstraint " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_USAGE);
		System.out.print(models3.length == 0 ? "" : "\n" + "Usage " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_USER_STORY);
		System.out.print(models3.length == 0 ? "" : "\n" + "UserStory " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_USE_CASE);
		System.out.print(models3.length == 0 ? "" : "\n" + "UseCase " + models3.length);

		models3 = projectManager.getProject()
				.toModelElementArray(IModelElementFactory.MODEL_TYPE_USE_CASE_DESCRIPTION_ITEM);
		System.out.print(models3.length == 0 ? "" : "\n" + "UseCaseDescriptionItem " + models3.length);

		models3 = projectManager.getProject()
				.toModelElementArray(IModelElementFactory.MODEL_TYPE_USE_CASE_DESCRIPTION_ITEM_CONTAINER);
		System.out.print(models3.length == 0 ? "" : "\n" + "UseCaseDescriptionItemContainer " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_USE_CASE_NOTE);
		System.out.print(models3.length == 0 ? "" : "\n" + "UseCaseNote " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_USE_CASE_NOTE_POINT);
		System.out.print(models3.length == 0 ? "" : "\n" + "UseCaseNotePoint " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_USE_CASE_STATEMENT);
		System.out.print(models3.length == 0 ? "" : "\n" + "UseCaseStatement " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_VALUE_PIN);
		System.out.print(models3.length == 0 ? "" : "\n" + "ValuePin " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_VALUE_SPECIFICATION);
		System.out.print(models3.length == 0 ? "" : "\n" + "ValueSpecification " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_VARIABLE);
		System.out.print(models3.length == 0 ? "" : "\n" + "Variable " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_VERIFY);
		System.out.print(models3.length == 0 ? "" : "\n" + "Verify " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_VIEWPOINT);
		System.out.print(models3.length == 0 ? "" : "\n" + "Viewpoint " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_VIEWPOINT_LOOKUP);
		System.out.print(models3.length == 0 ? "" : "\n" + "ViewpointLookup " + models3.length);

		models3 = projectManager.getProject()
				.toModelElementArray(IModelElementFactory.MODEL_TYPE_VIEWPOINT_STAKEHOLDER);
		System.out.print(models3.length == 0 ? "" : "\n" + "ViewpointStakeholder " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_VISIO_MODEL);
		System.out.print(models3.length == 0 ? "" : "\n" + "VisioModel " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_VOICE);
		System.out.print(models3.length == 0 ? "" : "\n" + "Voice " + models3.length);

		models3 = projectManager.getProject()
				.toModelElementArray(IModelElementFactory.MODEL_TYPE_WFDIOS_SPLIT_VIEW_PROFILE);
		System.out.print(models3.length == 0 ? "" : "\n" + "WFDIOSSplitViewProfile " + models3.length);

		models3 = projectManager.getProject()
				.toModelElementArray(IModelElementFactory.MODEL_TYPE_WFDIOS_TAB_BAR_PROFILE);
		System.out.print(models3.length == 0 ? "" : "\n" + "WFDIOSTabBarProfile " + models3.length);

		models3 = projectManager.getProject()
				.toModelElementArray(IModelElementFactory.MODEL_TYPE_WFDIOS_TOOLBAR_PROFILE);
		System.out.print(models3.length == 0 ? "" : "\n" + "WFDIOSToolbarProfile " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_WFDI_PAD_PROFILE);
		System.out.print(models3.length == 0 ? "" : "\n" + "WFDIPadProfile " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_WFDI_PHONE_PROFILE);
		System.out.print(models3.length == 0 ? "" : "\n" + "WFDIPhoneProfile " + models3.length);

		models3 = projectManager.getProject()
				.toModelElementArray(IModelElementFactory.MODEL_TYPE_WFD_ACCORDION_PANEL_PROFILE);
		System.out.print(models3.length == 0 ? "" : "\n" + "WFDAccordionPanelProfile " + models3.length);

		models3 = projectManager.getProject()
				.toModelElementArray(IModelElementFactory.MODEL_TYPE_WFD_ACTION_SHEET_PROFILE);
		System.out.print(models3.length == 0 ? "" : "\n" + "WFDActionSheetProfile " + models3.length);

		models3 = projectManager.getProject()
				.toModelElementArray(IModelElementFactory.MODEL_TYPE_WFD_ALERT_VIEW_PROFILE);
		System.out.print(models3.length == 0 ? "" : "\n" + "WFDAlertViewProfile " + models3.length);

		models3 = projectManager.getProject()
				.toModelElementArray(IModelElementFactory.MODEL_TYPE_WFD_ANDROID_DRAWER_PROFILE);
		System.out.print(models3.length == 0 ? "" : "\n" + "WFDAndroidDrawerProfile " + models3.length);

		models3 = projectManager.getProject()
				.toModelElementArray(IModelElementFactory.MODEL_TYPE_WFD_ANDROID_PHONE_PROFILE);
		System.out.print(models3.length == 0 ? "" : "\n" + "WFDAndroidPhoneProfile " + models3.length);

		models3 = projectManager.getProject()
				.toModelElementArray(IModelElementFactory.MODEL_TYPE_WFD_ANDROID_TABLET_PROFILE);
		System.out.print(models3.length == 0 ? "" : "\n" + "WFDAndroidTabletProfile " + models3.length);

		models3 = projectManager.getProject()
				.toModelElementArray(IModelElementFactory.MODEL_TYPE_WFD_ANNOTATION_PROFILE);
		System.out.print(models3.length == 0 ? "" : "\n" + "WFDAnnotationProfile " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_WFD_BADGE_PROFILE);
		System.out.print(models3.length == 0 ? "" : "\n" + "WFDBadgeProfile " + models3.length);

		models3 = projectManager.getProject()
				.toModelElementArray(IModelElementFactory.MODEL_TYPE_WFD_BREADCRUMB_PROFILE);
		System.out.print(models3.length == 0 ? "" : "\n" + "WFDBreadcrumbProfile " + models3.length);

		models3 = projectManager.getProject()
				.toModelElementArray(IModelElementFactory.MODEL_TYPE_WFD_BUTTON_GROUP_PROFILE);
		System.out.print(models3.length == 0 ? "" : "\n" + "WFDButtonGroupProfile " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_WFD_BUTTON_PROFILE);
		System.out.print(models3.length == 0 ? "" : "\n" + "WFDButtonProfile " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_WFD_CHECKBOX_PROFILE);
		System.out.print(models3.length == 0 ? "" : "\n" + "WFDCheckboxProfile " + models3.length);

		models3 = projectManager.getProject()
				.toModelElementArray(IModelElementFactory.MODEL_TYPE_WFD_COLLECTION_VIEW_PROFILE);
		System.out.print(models3.length == 0 ? "" : "\n" + "WFDCollectionViewProfile " + models3.length);

		models3 = projectManager.getProject()
				.toModelElementArray(IModelElementFactory.MODEL_TYPE_WFD_COMBO_BOX_PROFILE);
		System.out.print(models3.length == 0 ? "" : "\n" + "WFDComboBoxProfile " + models3.length);

		models3 = projectManager.getProject()
				.toModelElementArray(IModelElementFactory.MODEL_TYPE_WFD_DATE_PICKER_PROFILE);
		System.out.print(models3.length == 0 ? "" : "\n" + "WFDDatePickerProfile " + models3.length);

		models3 = projectManager.getProject()
				.toModelElementArray(IModelElementFactory.MODEL_TYPE_WFD_DESKTOP_FRAME_PROFILE);
		System.out.print(models3.length == 0 ? "" : "\n" + "WFDDesktopFrameProfile " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_WFD_DIALOG_PROFILE);
		System.out.print(models3.length == 0 ? "" : "\n" + "WFDDialogProfile " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_WFD_DROPDOWN_PROFILE);
		System.out.print(models3.length == 0 ? "" : "\n" + "WFDDropdownProfile " + models3.length);

		models3 = projectManager.getProject()
				.toModelElementArray(IModelElementFactory.MODEL_TYPE_WFD_GRID_VIEW_PROFILE);
		System.out.print(models3.length == 0 ? "" : "\n" + "WFDGridViewProfile " + models3.length);

		models3 = projectManager.getProject()
				.toModelElementArray(IModelElementFactory.MODEL_TYPE_WFD_GROUPED_TABLE_VIEW_PROFILE);
		System.out.print(models3.length == 0 ? "" : "\n" + "WFDGroupedTableViewProfile " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_WFD_IMAGE_PROFILE);
		System.out.print(models3.length == 0 ? "" : "\n" + "WFDImageProfile " + models3.length);

		models3 = projectManager.getProject()
				.toModelElementArray(IModelElementFactory.MODEL_TYPE_WFD_INPUT_GROUP_PROFILE);
		System.out.print(models3.length == 0 ? "" : "\n" + "WFDInputGroupProfile " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_WFD_LABEL_PROFILE);
		System.out.print(models3.length == 0 ? "" : "\n" + "WFDLabelProfile " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_WFD_LINE_PROFILE);
		System.out.print(models3.length == 0 ? "" : "\n" + "WFDLineProfile " + models3.length);

		models3 = projectManager.getProject()
				.toModelElementArray(IModelElementFactory.MODEL_TYPE_WFD_LIST_GROUP_PROFILE);
		System.out.print(models3.length == 0 ? "" : "\n" + "WFDListGroupProfile " + models3.length);

		models3 = projectManager.getProject()
				.toModelElementArray(IModelElementFactory.MODEL_TYPE_WFD_LIST_VIEW_PROFILE);
		System.out.print(models3.length == 0 ? "" : "\n" + "WFDListViewProfile " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_WFD_MENU_BAR_PROFILE);
		System.out.print(models3.length == 0 ? "" : "\n" + "WFDMenuBarProfile " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_WFD_MENU_PROFILE);
		System.out.print(models3.length == 0 ? "" : "\n" + "WFDMenuProfile " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_WFD_OVAL_PROFILE);
		System.out.print(models3.length == 0 ? "" : "\n" + "WFDOvalProfile " + models3.length);

		models3 = projectManager.getProject()
				.toModelElementArray(IModelElementFactory.MODEL_TYPE_WFD_PAGE_CONTROL_PROFILE);
		System.out.print(models3.length == 0 ? "" : "\n" + "WFDPageControlProfile " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_WFD_PANEL_PROFILE);
		System.out.print(models3.length == 0 ? "" : "\n" + "WFDPanelProfile " + models3.length);

		models3 = projectManager.getProject()
				.toModelElementArray(IModelElementFactory.MODEL_TYPE_WFD_PICKER_VIEW_PROFILE);
		System.out.print(models3.length == 0 ? "" : "\n" + "WFDPickerViewProfile " + models3.length);

		models3 = projectManager.getProject()
				.toModelElementArray(IModelElementFactory.MODEL_TYPE_WFD_PLAIN_TABLE_VIEW_PROFILE);
		System.out.print(models3.length == 0 ? "" : "\n" + "WFDPlainTableViewProfile " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_WFD_POLYGON_PROFILE);
		System.out.print(models3.length == 0 ? "" : "\n" + "WFDPolygonProfile " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_WFD_POPOVER_PROFILE);
		System.out.print(models3.length == 0 ? "" : "\n" + "WFDPopoverProfile " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_WFD_POPUP_PROFILE);
		System.out.print(models3.length == 0 ? "" : "\n" + "WFDPopupProfile " + models3.length);

		models3 = projectManager.getProject()
				.toModelElementArray(IModelElementFactory.MODEL_TYPE_WFD_PROGRESS_BAR_PROFILE);
		System.out.print(models3.length == 0 ? "" : "\n" + "WFDProgressBarProfile " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_WFD_PROGRESS_PROFILE);
		System.out.print(models3.length == 0 ? "" : "\n" + "WFDProgressProfile " + models3.length);

		models3 = projectManager.getProject()
				.toModelElementArray(IModelElementFactory.MODEL_TYPE_WFD_PROGRESS_VIEW_PROFILE);
		System.out.print(models3.length == 0 ? "" : "\n" + "WFDProgressViewProfile " + models3.length);

		models3 = projectManager.getProject()
				.toModelElementArray(IModelElementFactory.MODEL_TYPE_WFD_RADIO_BUTTON_PROFILE);
		System.out.print(models3.length == 0 ? "" : "\n" + "WFDRadioButtonProfile " + models3.length);

		models3 = projectManager.getProject()
				.toModelElementArray(IModelElementFactory.MODEL_TYPE_WFD_RANKING_BAR_PROFILE);
		System.out.print(models3.length == 0 ? "" : "\n" + "WFDRankingBarProfile " + models3.length);

		models3 = projectManager.getProject()
				.toModelElementArray(IModelElementFactory.MODEL_TYPE_WFD_RECTANGLE_PROFILE);
		System.out.print(models3.length == 0 ? "" : "\n" + "WFDRectangleProfile " + models3.length);

		models3 = projectManager.getProject()
				.toModelElementArray(IModelElementFactory.MODEL_TYPE_WFD_SCROLL_BAR_PROFILE);
		System.out.print(models3.length == 0 ? "" : "\n" + "WFDScrollBarProfile " + models3.length);

		models3 = projectManager.getProject()
				.toModelElementArray(IModelElementFactory.MODEL_TYPE_WFD_SEARCH_BAR_PROFILE);
		System.out.print(models3.length == 0 ? "" : "\n" + "WFDSearchBarProfile " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_WFD_SEEK_BAR_PROFILE);
		System.out.print(models3.length == 0 ? "" : "\n" + "WFDSeekBarProfile " + models3.length);

		models3 = projectManager.getProject()
				.toModelElementArray(IModelElementFactory.MODEL_TYPE_WFD_SEGMENTED_CONTROL_PROFILE);
		System.out.print(models3.length == 0 ? "" : "\n" + "WFDSegmentedControlProfile " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_WFD_SLIDER_PROFILE);
		System.out.print(models3.length == 0 ? "" : "\n" + "WFDSliderProfile " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_WFD_SPINNER_PROFILE);
		System.out.print(models3.length == 0 ? "" : "\n" + "WFDSpinnerProfile " + models3.length);

		models3 = projectManager.getProject()
				.toModelElementArray(IModelElementFactory.MODEL_TYPE_WFD_SPLIT_BAR_PROFILE);
		System.out.print(models3.length == 0 ? "" : "\n" + "WFDSplitBarProfile " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_WFD_STATE);
		System.out.print(models3.length == 0 ? "" : "\n" + "WFDState " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_WFD_STATE_CONTAINER);
		System.out.print(models3.length == 0 ? "" : "\n" + "WFDStateContainer " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_WFD_STATE_SCENARIO);
		System.out.print(models3.length == 0 ? "" : "\n" + "WFDStateScenario " + models3.length);

		models3 = projectManager.getProject()
				.toModelElementArray(IModelElementFactory.MODEL_TYPE_WFD_STATE_STEP_EXTENSION);
		System.out.print(models3.length == 0 ? "" : "\n" + "WFDStateStepExtension " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_WFD_STEPPER_PROFILE);
		System.out.print(models3.length == 0 ? "" : "\n" + "WFDStepperProfile " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_WFD_SWITCH_PROFILE);
		System.out.print(models3.length == 0 ? "" : "\n" + "WFDSwitchProfile " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_WFD_TABLE_PROFILE);
		System.out.print(models3.length == 0 ? "" : "\n" + "WFDTableProfile " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_WFD_TAB_HOST_PROFILE);
		System.out.print(models3.length == 0 ? "" : "\n" + "WFDTabHostProfile " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_WFD_TAB_PROFILE);
		System.out.print(models3.length == 0 ? "" : "\n" + "WFDTabProfile " + models3.length);

		models3 = projectManager.getProject()
				.toModelElementArray(IModelElementFactory.MODEL_TYPE_WFD_TEXT_FIELD_PROFILE);
		System.out.print(models3.length == 0 ? "" : "\n" + "WFDTextFieldProfile " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_WFD_TOASTS_PROFILE);
		System.out.print(models3.length == 0 ? "" : "\n" + "WFDToastsProfile " + models3.length);

		models3 = projectManager.getProject()
				.toModelElementArray(IModelElementFactory.MODEL_TYPE_WFD_TOGGLE_BUTTON_PROFILE);
		System.out.print(models3.length == 0 ? "" : "\n" + "WFDToggleButtonProfile " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_WFD_TOOLBAR_PROFILE);
		System.out.print(models3.length == 0 ? "" : "\n" + "WFDToolbarProfile " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_WFD_TREE_PROFILE);
		System.out.print(models3.length == 0 ? "" : "\n" + "WFDTreeProfile " + models3.length);

		models3 = projectManager.getProject()
				.toModelElementArray(IModelElementFactory.MODEL_TYPE_WFD_WEB_BROWSER_PROFILE);
		System.out.print(models3.length == 0 ? "" : "\n" + "WFDWebBrowserProfile " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_WF_SCENARIO);
		System.out.print(models3.length == 0 ? "" : "\n" + "WFScenario " + models3.length);

		models3 = projectManager.getProject()
				.toModelElementArray(IModelElementFactory.MODEL_TYPE_WF_SCENARIO_CONTAINER);
		System.out.print(models3.length == 0 ? "" : "\n" + "WFScenarioContainer " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_WF_WEB_PANEL);
		System.out.print(models3.length == 0 ? "" : "\n" + "WFWebPanel " + models3.length);

		models3 = projectManager.getProject()
				.toModelElementArray(IModelElementFactory.MODEL_TYPE_WORKFLOWMATIC_ASSIGNED_VARIABLE_CONTAINER);
		System.out.print(models3.length == 0 ? "" : "\n" + "WorkflowmaticAssignedVariableContainer " + models3.length);

		models3 = projectManager.getProject()
				.toModelElementArray(IModelElementFactory.MODEL_TYPE_WORKFLOWMATIC_AUTO_ASSIGN_VARIABLE);
		System.out.print(models3.length == 0 ? "" : "\n" + "WorkflowmaticAutoAssignVariable " + models3.length);

		models3 = projectManager.getProject()
				.toModelElementArray(IModelElementFactory.MODEL_TYPE_WORKFLOWMATIC_MANUAL_ASSIGN_VARIABLE);
		System.out.print(models3.length == 0 ? "" : "\n" + "WorkflowmaticManualAssignVariable " + models3.length);

		models3 = projectManager.getProject()
				.toModelElementArray(IModelElementFactory.MODEL_TYPE_WORKFLOWMATIC_VARIABLE);
		System.out.print(models3.length == 0 ? "" : "\n" + "WorkflowmaticVariable " + models3.length);

		models3 = projectManager.getProject()
				.toModelElementArray(IModelElementFactory.MODEL_TYPE_WORKFLOWMATIC_VARIABLE_CONTAINER);
		System.out.print(models3.length == 0 ? "" : "\n" + "WorkflowmaticVariableContainer " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_WSDL_BINDING);
		System.out.print(models3.length == 0 ? "" : "\n" + "WSDLBinding " + models3.length);

		models3 = projectManager.getProject()
				.toModelElementArray(IModelElementFactory.MODEL_TYPE_WSDL_BINDING_OPERATION);
		System.out.print(models3.length == 0 ? "" : "\n" + "WSDLBindingOperation " + models3.length);

		models3 = projectManager.getProject()
				.toModelElementArray(IModelElementFactory.MODEL_TYPE_WSDL_BINDING_OPERATION_FAULT);
		System.out.print(models3.length == 0 ? "" : "\n" + "WSDLBindingOperationFault " + models3.length);

		models3 = projectManager.getProject()
				.toModelElementArray(IModelElementFactory.MODEL_TYPE_WSDL_BINDING_OPERATION_INPUT);
		System.out.print(models3.length == 0 ? "" : "\n" + "WSDLBindingOperationInput " + models3.length);

		models3 = projectManager.getProject()
				.toModelElementArray(IModelElementFactory.MODEL_TYPE_WSDL_BINDING_OPERATION_OUTPUT);
		System.out.print(models3.length == 0 ? "" : "\n" + "WSDLBindingOperationOutput " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_WSDL_CONTAINER);
		System.out.print(models3.length == 0 ? "" : "\n" + "WSDLContainer " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_WSDL_DEFINITIONS);
		System.out.print(models3.length == 0 ? "" : "\n" + "WSDLDefinitions " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_WSDL_DOCUMENTATION);
		System.out.print(models3.length == 0 ? "" : "\n" + "WSDLDocumentation " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_WSDL_FAULT);
		System.out.print(models3.length == 0 ? "" : "\n" + "WSDLFault " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_WSDL_IMPORT);
		System.out.print(models3.length == 0 ? "" : "\n" + "WSDLImport " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_WSDL_INPUT);
		System.out.print(models3.length == 0 ? "" : "\n" + "WSDLInput " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_WSDL_MESSAGE);
		System.out.print(models3.length == 0 ? "" : "\n" + "WSDLMessage " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_WSDL_OPERATION);
		System.out.print(models3.length == 0 ? "" : "\n" + "WSDLOperation " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_WSDL_OUTPUT);
		System.out.print(models3.length == 0 ? "" : "\n" + "WSDLOutput " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_WSDL_PART);
		System.out.print(models3.length == 0 ? "" : "\n" + "WSDLPart " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_WSDL_PORT);
		System.out.print(models3.length == 0 ? "" : "\n" + "WSDLPort " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_WSDL_PORT_TYPE);
		System.out.print(models3.length == 0 ? "" : "\n" + "WSDLPortType " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_WSDL_SERVICE);
		System.out.print(models3.length == 0 ? "" : "\n" + "WSDLService " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_WSDL_TYPES);
		System.out.print(models3.length == 0 ? "" : "\n" + "WSDLTypes " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_XML_MODEL_ATTRIBUTE);
		System.out.print(models3.length == 0 ? "" : "\n" + "XMLModelAttribute " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_XML_MODEL_ELEMENT);
		System.out.print(models3.length == 0 ? "" : "\n" + "XMLModelElement " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_XML_MODEL_TEXT);
		System.out.print(models3.length == 0 ? "" : "\n" + "XMLModelText " + models3.length);

		models3 = projectManager.getProject()
				.toModelElementArray(IModelElementFactory.MODEL_TYPE_XML_NAMESPACE_ATTRIBUTE);
		System.out.print(models3.length == 0 ? "" : "\n" + "XMLNamespaceAttribute " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_XPDL_APPLICATION);
		System.out.print(models3.length == 0 ? "" : "\n" + "XPDLApplication " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_XPDL_DATA_FIELD);
		System.out.print(models3.length == 0 ? "" : "\n" + "XPDLDataField " + models3.length);

		models3 = projectManager.getProject()
				.toModelElementArray(IModelElementFactory.MODEL_TYPE_XPDL_FORMAL_PARAMETER);
		System.out.print(models3.length == 0 ? "" : "\n" + "XPDLFormalParameter " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_XPDL_IMPLEMENTATION);
		System.out.print(models3.length == 0 ? "" : "\n" + "XPDLImplementation " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_XPDL_TOOL);
		System.out.print(models3.length == 0 ? "" : "\n" + "XPDLTool " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_XSD_ALL);
		System.out.print(models3.length == 0 ? "" : "\n" + "XSDAll " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_XSD_ANNOTATION);
		System.out.print(models3.length == 0 ? "" : "\n" + "XSDAnnotation " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_XSD_ANY);
		System.out.print(models3.length == 0 ? "" : "\n" + "XSDAny " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_XSD_ANY_ATTRIBUTE);
		System.out.print(models3.length == 0 ? "" : "\n" + "XSDAnyAttribute " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_XSD_APPINFO);
		System.out.print(models3.length == 0 ? "" : "\n" + "XSDAppinfo " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_XSD_ATTRIBUTE);
		System.out.print(models3.length == 0 ? "" : "\n" + "XSDAttribute " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_XSD_ATTRIBUTE_GROUP);
		System.out.print(models3.length == 0 ? "" : "\n" + "XSDAttributeGroup " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_XSD_CHOICE);
		System.out.print(models3.length == 0 ? "" : "\n" + "XSDChoice " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_XSD_COMPLEX_CONTENT);
		System.out.print(models3.length == 0 ? "" : "\n" + "XSDComplexContent " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_XSD_COMPLEX_TYPE);
		System.out.print(models3.length == 0 ? "" : "\n" + "XSDComplexType " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_XSD_DOCUMENTATION);
		System.out.print(models3.length == 0 ? "" : "\n" + "XSDDocumentation " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_XSD_ELEMENT);
		System.out.print(models3.length == 0 ? "" : "\n" + "XSDElement " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_XSD_ENUMERATION);
		System.out.print(models3.length == 0 ? "" : "\n" + "XSDEnumeration " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_XSD_EXTENSION);
		System.out.print(models3.length == 0 ? "" : "\n" + "XSDExtension " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_XSD_FIELD);
		System.out.print(models3.length == 0 ? "" : "\n" + "XSDField " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_XSD_FRACTION_DIGITS);
		System.out.print(models3.length == 0 ? "" : "\n" + "XSDFractionDigits " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_XSD_GROUP);
		System.out.print(models3.length == 0 ? "" : "\n" + "XSDGroup " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_XSD_IMPORT);
		System.out.print(models3.length == 0 ? "" : "\n" + "XSDImport " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_XSD_INCLUDE);
		System.out.print(models3.length == 0 ? "" : "\n" + "XSDInclude " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_XSD_KEY);
		System.out.print(models3.length == 0 ? "" : "\n" + "XSDKey " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_XSD_KEYREF);
		System.out.print(models3.length == 0 ? "" : "\n" + "XSDKeyref " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_XSD_LENGTH);
		System.out.print(models3.length == 0 ? "" : "\n" + "XSDLength " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_XSD_LIST);
		System.out.print(models3.length == 0 ? "" : "\n" + "XSDList " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_XSD_MAX_EXCLUSIVE);
		System.out.print(models3.length == 0 ? "" : "\n" + "XSDMaxExclusive " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_XSD_MAX_INCLUSIVE);
		System.out.print(models3.length == 0 ? "" : "\n" + "XSDMaxInclusive " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_XSD_MAX_LENGTH);
		System.out.print(models3.length == 0 ? "" : "\n" + "XSDMaxLength " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_XSD_MIN_EXCLUSIVE);
		System.out.print(models3.length == 0 ? "" : "\n" + "XSDMinExclusive " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_XSD_MIN_INCLUSIVE);
		System.out.print(models3.length == 0 ? "" : "\n" + "XSDMinInclusive " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_XSD_MIN_LENGTH);
		System.out.print(models3.length == 0 ? "" : "\n" + "XSDMinLength " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_XSD_NOTATION);
		System.out.print(models3.length == 0 ? "" : "\n" + "XSDNotation " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_XSD_PATTERN);
		System.out.print(models3.length == 0 ? "" : "\n" + "XSDPattern " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_XSD_REDEFINE);
		System.out.print(models3.length == 0 ? "" : "\n" + "XSDRedefine " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_XSD_RESTRICTION);
		System.out.print(models3.length == 0 ? "" : "\n" + "XSDRestriction " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_XSD_SCHEMA);
		System.out.print(models3.length == 0 ? "" : "\n" + "XSDSchema " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_XSD_SELECTOR);
		System.out.print(models3.length == 0 ? "" : "\n" + "XSDSelector " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_XSD_SEQUENCE);
		System.out.print(models3.length == 0 ? "" : "\n" + "XSDSequence " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_XSD_SIMPLE_CONTENT);
		System.out.print(models3.length == 0 ? "" : "\n" + "XSDSimpleContent " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_XSD_SIMPLE_TYPE);
		System.out.print(models3.length == 0 ? "" : "\n" + "XSDSimpleType " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_XSD_TOTAL_DIGITS);
		System.out.print(models3.length == 0 ? "" : "\n" + "XSDTotalDigits " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_XSD_UNION);
		System.out.print(models3.length == 0 ? "" : "\n" + "XSDUnion " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_XSD_UNIQUE);
		System.out.print(models3.length == 0 ? "" : "\n" + "XSDUnique " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_XSD_WHITE_SPACE);
		System.out.print(models3.length == 0 ? "" : "\n" + "XSDWhiteSpace " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_XS_ATTRIBUTE_DETAIL);
		System.out.print(models3.length == 0 ? "" : "\n" + "XSAttributeDetail " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_XS_ATTRIBUTE_TYPE);
		System.out.print(models3.length == 0 ? "" : "\n" + "XSAttributeType " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_XS_CLASS_DETAIL);
		System.out.print(models3.length == 0 ? "" : "\n" + "XSClassDetail " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_XS_COMPLEX_TYPE);
		System.out.print(models3.length == 0 ? "" : "\n" + "XSComplexType " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_XS_DATA_TYPE);
		System.out.print(models3.length == 0 ? "" : "\n" + "XSDataType " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_XS_ELEMENT_TYPE);
		System.out.print(models3.length == 0 ? "" : "\n" + "XSElementType " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_XS_SIMPLE_TYPE);
		System.out.print(models3.length == 0 ? "" : "\n" + "XSSimpleType " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_ZACHMAN_CELL);
		System.out.print(models3.length == 0 ? "" : "\n" + "ZachmanCell " + models3.length);

		models3 = projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_ZACHMAN_CONTAINER);
		System.out.print(models3.length == 0 ? "" : "\n" + "ZachmanContainer " + models3.length);

		models3 = projectManager.getProject()
				.toModelElementArray(IModelElementFactory.MODEL_TYPE_ZACHMAN_DIAGRAM_CONTAINER);
		System.out.print(models3.length == 0 ? "" : "\n" + "ZachmanDiagramContainer " + models3.length);

	}

}
