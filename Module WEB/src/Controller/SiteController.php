<?php

namespace App\Controller;

use App\Repository\CategoryRepository;
use Doctrine\ORM\EntityManagerInterface;
use Doctrine\ORM\Mapping\Entity;
use phpDocumentor\Reflection\Types\Integer;
use SplFileObject;
use Symfony\Bridge\Doctrine\Form\Type\EntityType;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\Form\Extension\Core\Type\CheckboxType;
use Symfony\Component\Form\Extension\Core\Type\ChoiceType;
use Symfony\Component\Form\Extension\Core\Type\SubmitType;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use App\Entity\Category;
use App\Entity\Billet;
use Symfony\Component\Form\FormTypeInterface;
use App\Repository\BilletRepository;
use App\Entity\Film;
use Symfony\Component\Form\Extension\Core\Type\TextType;
use App\Repository\FilmRepository;


class SiteController extends AbstractController
{
    /**
     * @Route("/site", name="site")
     */
    public function index(): Response
    {
        return $this->render('site/index.html.twig', [
            'controller_name' => 'SiteController',
        ]);
    }

    /**
     * @Route("/", name="home")
     */
    public function home(): Response
    {
        return $this->render('site/home.html.twig', [
            'controller_name' => 'SiteController',
        ]);
    }

    /**
     * @Route("/boutique", name="boutique")
     */
    public function boutique(CategoryRepository $repo, Request $request, BilletRepository $repo1, EntityManagerInterface $manager): Response
    {
        $test = 0;
        $test1 = 0;
        $test2 = 0;
        $test3 = 0;
        $test4 = 0;
        $discount3 = 0;
        $discount = 0;
        $result = '';
        $result1 = '';

        //----------------------------------------- GRAND PUBLIC --------------------------------------------------

        $form = $this->createFormBuilder()
            ->add('day', ChoiceType::class, [
                'choices' => [
                    'Selectionner' => null,
                    'Dimanche' => 'dimanche',
                    'Lundi' => 'lundi',
                    'Mardi' => 'mardi',
                    'Mercredi' => 'mercredi',
                    'Jeudi' => 'jeudi',
                    'Vendredi' => 'vendredi',
                    'Samedi' => 'samedi',
                ],
                'label' => ' '
            ])
            ->add('cat', ChoiceType::class, [
                'choices' => [
                    'Selectionner' => null,
                    'Catégorie 1' => 'cat1',
                    'Catégorie 2' => 'cat2',
                ],
                'label' => ' '
            ])
            ->add('tar', ChoiceType::class, [
                'choices' => [
                    'Selectionner' => null,
                    'Tarif Adulte' => 'price',
                    'Tarif Enfant (Moins de 12 ans)' => 'price_children',
                ],
                'label' => ' '
            ])
            ->add('submit', SubmitType::class, [
                'label' => 'Valider !',
                'attr' => ['class' => 'btn btn-primary']
            ])
            ->getForm();

        $price = 0;
        $test = 0;
        $form->handleRequest($request);


        if ($form->isSubmitted() && $form->isValid()) {
            $data = $form->getData();
            $category = $repo->findOneBy(array('day' => $data['day'], 'name' => $data['cat']));
            if ($data['tar'] == 'price') {
                $price = $category->getPrice();
            }
            if ($data['tar'] == 'price_children') {
                $price = $category->getPriceChildren();
                }
            $test = 1;
            $billet = $repo1->find(1);
            $nb_dispo = $billet->getNbDispo();
            $nb_dispo = $nb_dispo - 1;
            $billet->setNbDispo($nb_dispo);
            $manager->persist($billet);
            $manager->flush();
            }

            //----------------------------------- LICENCIE -------------------------------------------------------

            $file = new SplFileObject("assets/file.txt");

            $form2 = $this->createFormBuilder()
                ->add('day2', ChoiceType::class, [
                    'choices' => [
                        'Selectionner' => null,
                        'Dimanche' => 'dimanche',
                        'Lundi' => 'lundi',
                        'Mardi' => 'mardi',
                        'Mercredi' => 'mercredi',
                        'Jeudi' => 'jeudi',
                        'Vendredi' => 'vendredi',
                        'Samedi' => 'samedi',
                    ],
                    'label' => ' '
                ])
                ->add('cat2', ChoiceType::class, [
                    'choices' => [
                        'Selectionner' => null,
                        'Catégorie 1' => 'cat1',
                        'Catégorie 2' => 'cat2',
                    ],
                    'label' => ' '
                ])
                ->add('tar2', ChoiceType::class, [
                    'choices' => [
                        'Selectionner' => null,
                        'Tarif Adulte' => 'price',
                        'Tarif Enfant (Moins de 12 ans)' => 'price_children',
                    ],
                    'label' => ' '
                ])
                ->add('code2', TextType::class, [
                    'label' => ' ',
                ])
                ->add('submit2', SubmitType::class, [
                    'label' => 'Valider le code',
                    'attr' => ['class'=>'btn btn-outline-primary']
                ])
                ->getForm();



            $form2->handleRequest($request);


            if ($form2->isSubmitted() && $form2->isValid()){
                $result = "le code n'est pas valide.";
                $test1=0;
                $discount = 0;
                $test2=0;
                $code = $form2->getData();
                while(!$file->eof())
                {
                    if ($file->fgets()==$code['code2']){
                        $result = 'Le code est valide.';
                        $billet = $repo1->find(2);
                        $discount = $billet->getDiscount();
                        $test1 = 1;
                        break;
                    }

                }
                $file = null;
                $billet = $repo1->find(2);
                $nb_dispo = $billet->getNbDispo();
                $nb_dispo = $nb_dispo - 1;
                $billet->setNbDispo($nb_dispo);
                $manager->persist($billet);
                $manager->flush();
                $data = $form2->getData();
                $category = $repo->findOneBy(array('day' => $data['day2'], 'name' => $data['cat2']));
                if ($data['tar2'] == 'price') {
                    $price = $category->getPrice();
                    $price = $price - ($discount*($price))/100;
                }
                if ($data['tar2'] == 'price_children') {
                    $price = $category->getPriceChildren();
                    $price = $price - ($discount*($price))/100;                }
                $test2 = 1;

            }

            // ----------------------------------------- Associations ------------------------------------------------

        $file2 = new SplFileObject("assets/file2.txt");

        $form4 = $this->createFormBuilder()
            ->add('day4', ChoiceType::class, [
                'choices' => [
                    'Selectionner' => null,
                    'Dimanche' => 'dimanche',
                    'Lundi' => 'lundi',
                    'Mardi' => 'mardi',
                    'Mercredi' => 'mercredi',
                    'Jeudi' => 'jeudi',
                    'Vendredi' => 'vendredi',
                    'Samedi' => 'samedi',
                ],
                'label' => ' '
            ])
            ->add('cat4', ChoiceType::class, [
                'choices' => [
                    'Selectionner' => null,
                    'Catégorie 1' => 'cat1',
                    'Catégorie 2' => 'cat2',
                ],
                'label' => ' '
            ])
            ->add('tar4', ChoiceType::class, [
                'choices' => [
                    'Selectionner' => null,
                    'Tarif Adulte' => 'price',
                    'Tarif Enfant (Moins de 12 ans)' => 'price_children',
                ],
                'label' => ' '
            ])
            ->add('code4', TextType::class, [
                'label' => ' ',
            ])
            ->add('submit4', SubmitType::class, [
                'label' => 'Valider le code',
                'attr' => ['class'=>'btn btn-outline-primary']
            ])
            ->getForm();



        $form4->handleRequest($request);


        if ($form4->isSubmitted() && $form4->isValid()){
            $result1 = "le code n'est pas valide.";
            $discount3 = 0;
            $test3=0;
            $test4 = 0;
            $code = $form4->getData();
            while(!$file->eof())
            {
                if ($file2->fgets()==$code['code4']){
                    $result1 = 'Le code est valide.';
                    $billet = $repo1->find(3);
                    $discount3 = $billet->getDiscount();
                    $test4 = 1;
                    break;
                }

            }
            $file2 = null;
            $billet = $repo1->find(3);
            $nb_dispo3 = $billet->getNbDispo();
            $nb_dispo3 = $nb_dispo3 - 1;
            $billet->setNbDispo($nb_dispo3);
            $manager->persist($billet);
            $manager->flush();
            $data = $form4->getData();
            $category = $repo->findOneBy(array('day' => $data['day4'], 'name' => $data['cat4']));
            if ($data['tar4'] == 'price') {
                $price = $category->getPrice();
                $price = $price - ($discount3*($price))/100;
                $test3 = 1 ;
            }
            if ($data['tar4'] == 'price_children') {
                $price = $category->getPriceChildren();
                $price = $price - ($discount3*($price))/100;
                $test3 = 1 ;
            }
            $test4 = 1;

        }
            // ----------------------------------------- Invitations ---------------------------------------------------------

        $form5 = $this->createFormBuilder()
            ->add('day5', ChoiceType::class, [
                'choices' => [
                    'Selectionner' => null,
                    'Dimanche' => 'dimanche',
                    'Lundi' => 'lundi',
                    'Mardi' => 'mardi',
                    'Mercredi' => 'mercredi',
                    'Jeudi' => 'jeudi',
                    'Vendredi' => 'vendredi',
                    'Samedi' => 'samedi',
                ],
                'label' => ' '
            ])
            ->add('name5', TextType::class, [
                'label' => ' '
            ])
            ->add('lastname5', TextType::class, [
                'label' => ' '
            ])
            ->add('submit5', SubmitType::class, [
                'label' => 'Valider !',
                'attr' => ['class' => 'btn btn-primary']
            ])

            ->getForm();



        return $this->render('site/boutique.html.twig', [
                'form' => $form->createView(),
                'price' => $price,
                'test' => $test,
                'form2'=> $form2->createView(),
                'result' => $result,
                'test1' => $test1,
                'discount' => $discount,
                'test2'=> $test2,
                'form4'=> $form4->createView(),
                'form5'=> $form5->createView(),
                'discount3' => $discount3,
                'test3' => $test3,
                'test4' => $test4,
                'result1' => $result1,
            ]);

    }

    /**
     * @Route("/joueurs", name="joueurs")
     */
    public function players(): Response
    {
        return $this->render('site/players.html.twig', [
            'controller_name' => 'SiteController',
        ]);
    }

    /**
     * @Route("/informations", name="infos")
     */
    public function info(): Response
    {
        return $this->render('site/info.html.twig', [
            'controller_name' => 'SiteController',
        ]);
    }

    /**
     * @Route("/contact", name="contact")
     */
    public function contact(): Response
    {
        return $this->render('site/contact.html.twig', [
            'controller_name' => 'SiteController',
        ]);
    }

    /**
     * @Route("/associations", name="asso")
     */
    public function asso(): Response
    {
        return $this->render('site/asso.html.twig', [
            'controller_name' => 'SiteController',
        ]);
    }

    /**
     * @Route("/redirect", name="redirect")
     */
    public function redirection(): Response
    {
        return $this->render('site/redirect.html.twig', [
            'controller_name' => 'SiteController',
        ]);
    }

}
